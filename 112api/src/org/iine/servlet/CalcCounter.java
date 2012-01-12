package org.iine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CalcCounter extends HttpServlet{
	private static final long serialVersionUID = -5452842651326338208L;

	
	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			this.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void save() throws Exception{
		Entity entity  = new Entity("MyLog");
		entity.setProperty("time", 0);
		ds.put(entity);
		
	}

	
}
