package org.iine.servlet.twitter;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;


public class TwSigninServlet extends HttpServlet{
	private static final long serialVersionUID = -8835049885983720041L;
	
	private final String consumerKey = "ld0SJKxbtcTlnEIgjb8Zw";
	private final String consumerSecret = "k1kR1Fd480lBNunbo5HfgOZgIHFIdUEveR5xuokpxMo";
	private final String url = "http://www.112api.jp/twcb";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		String redirectUrl = req.getParameter("u");
		String params = req.getQueryString();
		
		final Twitter twitter = new TwitterFactory().getInstance();
		session.setAttribute("twitter", twitter);
		StringBuffer callbackURL = req.getRequestURL();
		int index = callbackURL.lastIndexOf("/");
		callbackURL.replace(index, callbackURL.length(), "").append("/twcb");
		//callbackURL.toString()
		try {
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			//本番環境用なのでコメントアウト
			//RequestToken requestToken = twitter.getOAuthRequestToken(url);
			//開発時のテスト用コード
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString()/*url*/); 
			session.setAttribute("requestToken", requestToken);
			session.setAttribute("redirectUrl", redirectUrl);
			session.setAttribute("params", params);
			resp.sendRedirect(requestToken.getAuthenticationURL());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	
}
