package org.iine.page;

import org.iine.writer.HtmlBaseWriter;

public class NoAddressPage extends HtmlBaseWriter{

	@Override
	protected void writeBody() {
		// TODO Auto-generated method stub
		super.writeBody();
		
		html
		.append("このページはないよ");
	}

	
	
}
