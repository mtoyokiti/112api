package org.iine.writer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 
 * 最後にwriteHtmlを読んでください
 * 
 * @author kuro
 *
 */
public class HtmlBaseWriter {
	protected PrintWriter w;
	protected String userAgent;
	protected HttpSession session;
	protected boolean isMobile;
	protected StringBuilder html;
	protected HttpServletRequest req;
	
	
	
	
	
	
	
	public void http(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.req = req;
		
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		this.w = resp.getWriter();
		userAgent = req.getHeader("User-Agent");
		session = req.getSession();
	}
	
	
	
	/**
	 * Httpリクエストのパラメータを取得できます
	 * 
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return req.getParameter(key);
	}
	
	
	
	
	
	/**
	 * HTMLデータをブラウザに送信します
	 */
	public void writeHtml() {
		html = new StringBuilder();
		
		html.append("<html>")
		.append("<head>");
		this.writeHead();
		html.append("<script>");
		writeJavaScript();
		html.append("</script>");
		html.append("</head>");
		w.write(html.toString());
		
		// <body>
		html.setLength(0);
		html.append("<body  id='cssHappyLife' class='indexBody'>");
		this.writeBodyHeader();
		this.writeBody();
		this.writeBodyFooter();
		html.append("</body></html>");
		w.write(html.toString());
		html.setLength(0);
	}
	
	

	/**
	 * 生のJavaScriptを追加する場合はこのメソッドを実装してください
	 */
	protected void writeJavaScript() {
	}
	
	
	
	
	/**
	 * &lt;head>の内容を追加したい場合はこのメソッドを実装してください
	 */
	protected void writeHead(){
		html
		.append("	<meta http-equiv='content-type' content='text/html; charset=utf-8' />	")
		.append("	<title>イイネ！API！</title>	")
		.append("	<meta http-equiv='content-style-type' content='text/css' />	")
		.append("	<meta http-equiv='content-script-type' content='text/javascript' />	")
		.append("	<meta name='keywords' content='CSSハック,hack' />	")
		.append("	<meta name='description' content='最近のブラウザ（2008年01月くらい）で使えそうなCSSハックをまとめてます。' />	")
		.append("	<link href='mailto:hoge&#64;example.com' rev='made' />	")
		.append("	<link href='/index.html' rel='index' />	")
		.append("	<link href='/img/share/favicon.png' rel='icon' type='image/png' />	")
		.append("	<link href='/img/share/favicon.ico' rel='shortcut icon' />	")
		.append("		")
		.append("	<!-- stylesheet -->	")
		.append("	<link href='/css/import.css' rel='stylesheet' type='text/css' media='all' />	")
		;


	};
	
	
	
	
	
	protected void writeBodyHeader(){
		html
		.append("		")
		.append("	<div id='page'>	")
		.append("		")
		.append("	<span id='navSkip'><a href='#main' tabindex='1' title='本文へジャンプ'><img src='/img/share/skip.gif' alt='本文へジャンプ' width='1' height='1' /></a></span>	")
		.append("		")
		.append("	<div id='header'>	")
		.append("	<div class='area'>	")
		.append("		")
		.append("		")
		.append("	<h1 style='margin:0px; margin-bottom:-10px'><img src='/img/share/title.gif' /></h1>	")
		.append("	</div>	")
		.append("	<!-- / #header --></div>	")
		.append("		")
		.append("		")
		.append("	<div id='contents'>	")
		.append("	<div class='area'>	")
		.append("		")
		.append("	<div id='main'>	")


		
					;	
	}
	
	
	
	
	protected void writeBody() {
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
	}
	
	
	
	
	protected void writeBodyFooter() {
		html
		.append("		")
		.append("	<!-- / #main --></div>	")
		.append("		")
		.append("		")
		.append("		")
		.append("	<p class='pageTop'><a href='#page' title='このページの先頭へ'>ページトップへ</a></p>	")
		.append("		")
		.append("	</div>	")
		.append("	<!-- / #contents --></div>	")
		.append("		")
		.append("		")
		.append("	<div id='footer'>	")
		.append("	<div class='area'>	")
		.append("	<p class='copy'>this site is licensed under a <a href='http://creativecommons.org/licenses/by-nc-sa/2.1/jp/' title='Creative Commons License 表示-非営利 2.1 日本'>Creative Commons License</a>. * But, a part of contents is excluded.</p>	")
		.append("	</div>	")
		.append("	<!-- / #footer --></div>	")
		.append("		")
		.append("	<!-- / #page --></div>	")
		;

	}
	

}
