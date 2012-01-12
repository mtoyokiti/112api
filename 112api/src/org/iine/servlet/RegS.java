package org.iine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.page.RegPage;

public class RegS extends  HttpServlet{
	private static final long serialVersionUID = 5768042344043027131L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RegPage p = new RegPage();
		p.http(req, resp);
		p.writeHtml();
	}

	
}
