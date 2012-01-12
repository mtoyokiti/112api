package org.iine.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.component.UrlManagerC;

public class RegTitleS extends HttpServlet{
	private static final long serialVersionUID = 4892202511690867285L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter w =  resp.getWriter();
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String title = req.getParameter("title");
		
		if (isEmpty(id)) return ;
		if (isEmpty(pass))	return;
		if (isEmpty(title)) return;
		
		long idL = 0;
		try {
			idL = Long.parseLong(id);
		} catch (Exception e) {
			w.print("設定に失敗しました。");
			return;
		}
		
		UrlManagerC url = new UrlManagerC(idL);
		url.setTitle(title);
		url.write();
		
		w.print("設定しました。");
		
	}
	
	
	
	private boolean isEmpty(String val) {
		return ((val == null) || (val.equals("")));
	}

	
	
}
