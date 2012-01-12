package org.iine.writer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginWriter extends HtmlBaseWriter{

	/**
	 * ログイン済みかどうかのチェック
	 */
	public boolean isLogin = false;
	public String loginName = "";	
	public long loginId = 0;
	
	
	@Override
	public void http(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.http(req, resp);
		
		
		isLogin = false;
		
		// ログイン中かどうかのチェック
		String name = (String)session.getAttribute("name");

		if ((name != null) && (!"".equals(name))) {
			isLogin = true;
			loginName = name;
			loginId = (Long) session.getAttribute("uid");
		}
	}




	@Override
	protected void writeBody() {
		super.writeBody();
	}

	
	
}
