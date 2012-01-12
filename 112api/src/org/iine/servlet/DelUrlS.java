package org.iine.servlet;

import java.io.IOException;

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

public class DelUrlS extends HttpServlet {
	private static final long serialVersionUID = 6960043320551398680L;
	private long uid = 0;
	private long index = 0;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String indexS = req.getParameter("index");
		try {
			index = Long.parseLong(indexS);
		} catch (Exception e) {
			
		}
		HttpSession session = req.getSession();
		uid = (Long)session.getAttribute("uid");
		del();
		
	}

	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	

	
	
	private void del() {
		if (index == 0) {
			return;
		}
		
		Key parentKey = KeyFactory.createKey( "TAG", 1L);
		Key key = KeyFactory.createKey(parentKey, "SiteList", index);
		Query q = new Query("SiteList", parentKey);
		q.addFilter("uid", FilterOperator.EQUAL, uid);
		
		// 登録ユーザと削除ユーザが一致しているかどうか
		PreparedQuery pq = ds.prepare(q);
		boolean checkOk = false;
		for (Entity result : pq.asIterable()) {
			checkOk = true;
			break;
		}
		if (!checkOk) {
			return;
		}
		
		// 削除
		Key[] keys = new Key[]{key};
		ds.delete(keys);
	}
	
	
}
