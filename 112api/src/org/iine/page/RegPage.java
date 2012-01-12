package org.iine.page;

import org.iine.writer.MustLoginWriter;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * 登録用のページ
 * 
 * @author kuro
 *
 */
public class RegPage extends MustLoginWriter {


	
	
	@Override
	protected void writeLogin() {
		super.writeLogin();
		
		html
		//.append("<form action='reg_' method='post'>")
		.append("サイト登録<br><br>")
		.append("サイトタイトル<br>")
		.append("<input type='text' id='title' size=80><br>")
		.append("サイトアドレス<br>")
		.append("<input type='text' id='url' size=40 value='http://'>")
		.append("<button onclick='reg()'>登録</button>")
		.append("<hr>")
		;
		
		this.writeUrls();
	}
	
	


	@Override
	protected void writeJavaScript() {
		super.writeJavaScript();
		
		html.append("$(function(){writeUserUrlList();});");
	}




	private void writeUrls() {
		
		html.append("<br><br>");
		
		Key k1 = KeyFactory.createKey("TAG", 1);
		Query q = new Query("SiteList", k1);
		q.addFilter("uid", FilterOperator.EQUAL, this.loginId);
		html.append("<div id='urllist'>");
		html.append("</div>");
		
	}




	@Override
	protected void writeNotLogin() {
		html
		.append("<div style='text-align:center;'>")
		.append("サイトの登録にはTwitterIDでのログインが必要です。<p>")
		.append("</div>")
		
		;
		super.writeNotLogin();
	}

	
	

	@Override
	protected void writeHead() {
		super.writeHead();
		html.append("<script type='text/javascript' src='/js/reg.js'></script>")
		.append("<script type='text/javascript' src='/js/jquery161.js'></script>")
		.append("<style>")
		.append(".del{cursor:pointer;cursor:hand}")
		.append("</style>");

	}
	
	
	
	
	
	
	
	
	
}


