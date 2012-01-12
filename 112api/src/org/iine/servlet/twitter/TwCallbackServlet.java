package org.iine.servlet.twitter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;


public class TwCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 7728698083725309042L;

	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

	private final Logger LOG = Logger.getLogger(this.getClass().getName());
	private String redirectUrl_str = "/t/t";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session;
		
//		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		Twitter twitter = (Twitter) session.getAttribute("twitter");
		RequestToken requestToken = (RequestToken)session.getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		
		if (twitter == null) {
			response.getWriter().write("error cs. twitter == null");
			return;
		}
		
		try {
			twitter.getOAuthAccessToken(requestToken, verifier);
			session.removeAttribute("requestToken");
		} catch (TwitterException e) {
			throw new ServletException(e);
		}
		try {
			readData(twitter, session);
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
			return;
		}
		Object redirectUrl = session.getAttribute("redirectUrl");
		Object params = session.getAttribute("params");
		session.removeAttribute("redirectUrl");
		session.removeAttribute("params");
		
		String params_str = "";
		if (params != null) {params_str = "?" + params.toString();}
		
		if ((redirectUrl != null) && (!redirectUrl.equals(""))) {
			redirectUrl_str = (String) redirectUrl;
		}
		
		response.sendRedirect(request.getContextPath()
				+ redirectUrl_str + params_str);
		
		
//		response.getWriter().println(session.getAttribute("name"));
		
		
//		try {
//			writeData(twitter);
//		} catch (Exception e) {
//			response.getWriter().println(e.toString());
//		}
	}
	
	
	private void readData(Twitter tw, HttpSession session) throws Exception{
		long tw_id = tw.getId();
		long id = 0;
		String screenName = tw.getScreenName();
		
		Query q = new Query("U");
		//y.addFilter("postUser", FilterOperator.EQUAL, reply.getUser().getScreenName());
		q.addFilter("tw_id", FilterOperator.EQUAL, tw_id);
		PreparedQuery pq = ds.prepare(q);
		Entity e = pq.asSingleEntity();
		Key userKey = null;
		if (e == null) {
			// 新しくユーザを保存する
			Entity entity = new Entity("U");
			entity.setProperty("tw_id", tw_id);
			entity.setProperty("name", screenName);
			userKey = ds.put(entity);
			id = userKey.getId();
		} else {
			// 名前が変更されている可能性があるので、名前だけ保存する
			e.setProperty("name", screenName);
			userKey = ds.put(e);
			id = e.getKey().getId();
		}
		session.setAttribute("ukey", userKey);
		session.setAttribute("uid", id);
		session.setAttribute("name", screenName);
		
		
		
	}
		
	
	
}
