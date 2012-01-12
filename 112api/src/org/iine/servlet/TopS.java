package org.iine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.page.TopPage;

public class TopS extends  HttpServlet {
	private static final long serialVersionUID = 4571798521224655283L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TopPage p  = new TopPage();
		p.http(req, resp);
		p.writeHtml();		
	}

}
