package org.iine.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.writer.HtmlBaseWriter;

public class LinkPage extends HtmlBaseWriter {

	private long id = 0;
	
	
	@Override
	public void http(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object i = req.getAttribute("i");
		if (i == null) {
		} else {
			id = (Long)i;
		}
		super.http(req, resp);
		
	}

	
	
	
	
	@Override
	protected void writeBody() {
		super.writeBody();
		
		if (id == 0) {
			return;
		}
		
		html.append("id = " + id);
		
		
	}

	
	
}
