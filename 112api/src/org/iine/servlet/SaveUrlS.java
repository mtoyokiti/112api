package org.iine.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SaveUrlS extends HttpServlet {
	private static final long serialVersionUID = -6848810481996702390L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	
	private String title = "";
	private String url = "";
	/**
	 * twitterのuser id
	 */
	private long uid = 0;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter w = resp.getWriter();
		url = req.getParameter("url");
		title = req.getParameter("title");
		HttpSession session = req.getSession();
		Long uid_l = (Long)session.getAttribute("uid");
		if (uid_l == null) {
			return;
		}
		
		uid = uid_l.longValue();
		
		
		
		try {
			save();
		} catch (Exception e) {
			e.printStackTrace(w);
			return;
		}
		
		w.write("{\"r\":\"OK\"}");
	}
	
	
	
	private DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	
	
	
	private void save() throws Exception {
		
		if (url.startsWith("http://")) {
		} else if (url.startsWith("https://")) {
		} else {
			return;
		}
		
		if (uid == 0) {
			return;
		}
		
		
		if (title.equals("")) {
			return;
		}
		
		Key key = KeyFactory.createKey( "TAG", 1L);
		Entity pe = new Entity(key);
		ds.put(pe);
		
		Entity entity = new Entity("SiteList", key);
		entity.setProperty("title", title);
		entity.setProperty("url", url);
		entity.setProperty("uid", uid);
		
		ds.put(entity);
		
		
		
	}
	
	
	
	
	
	
	
}
