package org.iine.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.page.SaveUrlP;

/**
 * カウンタサーブレット
 * 
 * @author kuro
 *
 */
public class CounterUpS  extends HttpServlet{
	private static final long serialVersionUID = -8719147329307442677L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		SaveUrlP s =  new SaveUrlP();
		s.http(req, resp);
		
		
	}
	
	
	
	
}
