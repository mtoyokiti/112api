package org.iine.writer;

public class MustLoginWriter extends LoginWriter{

	
	
	
	
	@Override
	protected void writeBody() {
		super.writeBody();
		
		if (!isLogin) {
			this.writeNotLogin();
			return;
		}
		
		this.writeLogin();
	}




	/**
	 * ログイン済みの画面を描写する場合
	 */
	protected void writeLogin() {
		
	}
	
	
	
	
	/**
	 * ログインしていない場合の画面を描写する場合
	 */
	protected void writeNotLogin() {
		html
		.append("<div style='text-align:center;'>")
		.append("<br><br>")
		.append("<a href='/tw?u=/reg'>Twitter認証へすすむ</a>")
		.append("</div>")
		;
	}
}
