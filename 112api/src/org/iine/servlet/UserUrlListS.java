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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class UserUrlListS extends HttpServlet {
	private static final long serialVersionUID = 1236380768143634780L;
	private long uid = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		uid = (Long)session.getAttribute("uid");
		PrintWriter pw = resp.getWriter();
		pw.print(createList().toString());		
	}
	
	
	
	
	private StringBuilder createList() {
		StringBuilder sb = new StringBuilder();
		sb
		.append("{\"data\":[");
		
		Key k1 = KeyFactory.createKey("TAG", 1);
		Query q = new Query("SiteList", k1);
		q.addFilter("uid", FilterOperator.EQUAL, uid);
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = ds.prepare(q);
		boolean isFirst = true;
		for (Entity result : pq.asIterable()) {
			if (!isFirst) {
				sb.append(",");
			} 
			isFirst = false;
			String title = (String) result.getProperty("title");
			title.replace("\"", "");
			title.replace("\r", "");
			title.replace("\n", "");
			title.replace("\t", "");
			long id = result.getKey().getId();
			sb.append(id)
			.append(",\"")
			.append(title)
			.append("\"")
			;

		}

		sb.append("]}");
		return sb;
	}

	
//	Key k1 = KeyFactory.createKey("TAG", 1);
//	Query q = new Query("SiteList", k1);
//	q.addFilter("uid", FilterOperator.EQUAL, this.loginId);
//	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//	PreparedQuery pq = datastore.prepare(q);
//	html.append("<div id='urllist'>");
//	for (Entity result : pq.asIterable()) {
//		String title = (String) result.getProperty("title");
//		long id = result.getKey().getId();
//		html
//		.append("<img src='img/del.png' onclick='del(")
//		.append(id)
//		.append(")' class='del'>")
//		.append(title)
//		.append("<br>");
//	}
//	html.append("</div>");
	
}
