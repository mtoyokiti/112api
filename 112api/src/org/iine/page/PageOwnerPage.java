package org.iine.page;

import org.iine.writer.HtmlBaseWriter;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class PageOwnerPage extends HtmlBaseWriter{
	

	protected void writeBody() {
		
		String id = this.getParameter("id");
		
		
		
		html.append("登録ありがとうございます。<BR>" +
				"まずはじめにタイトルを登録してください。" +
				"<p>URL：" +
				"<p>タイトル：<input type=text size=30 name=title>" +
				"<p>" +
				"<button onclick='reg'>登録</button>");
	}

	
}
