package org.iine.page;

import org.iine.writer.HtmlBaseWriter;

/**
 * @author kuro
 *
 */
public class SetTitlePage extends HtmlBaseWriter{

	long id = 0;
	String pass = "";
	String url = "";
	
	public SetTitlePage(long id, String pass, String url) {
		this.id = id;
		this.pass = pass;
		this.url = url;
	}
	
	@Override
	protected void writeBodyHeader() {
		// 
	}
	@Override
	protected void writeBodyFooter() {
	}

	@Override
	protected void writeBody() {
		html
		.append("<form action='/regTitle' method='post'>" +
				"登録ありがとうございます。<BR>" +
				"まずはじめにタイトルを登録してください。" +
				"<p>URL：" +url +
				"<p>タイトル：<input type=text size=30 name=title>" +
				"<p>" +
				"<input type='submit' value='登録'>" +
				"<input type=hidden name=id value='"+id+"'>" +
				"<input type=hidden name=pass value='"+pass+"'>" +
				"</form>");
	}


	

	

}
