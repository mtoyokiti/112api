package org.iine.page;

import org.iine.writer.HtmlBaseWriter;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TopPage extends HtmlBaseWriter {

	private static DatastoreService ds = 
		DatastoreServiceFactory.getDatastoreService();
	 
	
	
	
	@Override
	protected void writeBody() {
		super.writeBody();
		html
		.append("<ul class='tagSearch03'>");
		writeIine();
		html.append("</ul>");
	}

	
	
	private void writeIine() {
		Query q = new Query("SiteList");
		PreparedQuery pq = ds.prepare(q);

		
		int i=0;
		for (Entity e: pq.asIterable()) {
			String title = (String)e.getProperty("title");
			String url = (String)e.getProperty("url");
			i ++;
			if (title == null) {
				continue;
			}
			html
			.append("<li class='tagRank")
			.append(i)
			.append("'>")
			.append("<a href='")
			.append(url)
			.append("' title=''>")
			.append(title)
			.append("</a></li>")
			;

		}
	}
	
	
	
	

	
	
}
