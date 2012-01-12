package org.iine.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import org.iine.writer.HtmlBaseWriter;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ReRankPage extends HtmlBaseWriter {

	private static DatastoreService ds = 
		DatastoreServiceFactory.getDatastoreService();
	
	@Override
	protected void writeBody() {
		super.writeBody();
		
		
		
		Key key = KeyFactory.createKey( "TAG", 1L);
		//Entity pe = new Entity(key);
		
		Query q = new Query("SiteList");
		//q.addSort("d");
		PreparedQuery pq = ds.prepare(q);

		
		LinkedList<Rank> lr = new LinkedList<Rank>();
		
		
		ArrayList<Rank> ranks = new ArrayList<Rank>();
		for (Entity e: pq.asIterable()) {
			String title = (String)e.getProperty("title");
			String url = (String)e.getProperty("url");
			
			
			Query q1 = new Query(e.getKey());
			//q.addSort("d");
			PreparedQuery pq1 = ds.prepare(q);
			int count = 0;
			for (Entity e1: pq1.asIterable()) {
				count++;
			}
			
			Rank rank = new Rank();
			rank.setEntity(e);
			rank.setCount(count);
			ranks.add(rank);
		

		}
		
		
		 Comparator<Rank> asc = new Comparator<Rank>() {
			@Override
			public int compare(Rank o1, Rank o2) {
				Rank rank0 = (Rank) o1;
				Rank rank1 = (Rank) o2;
		    	 int count0 = rank0.getCount();
		    	 int count1 = rank1.getCount();
		         return count0 - count1;
			}
		 };
		 
		 
		 
		 Arrays.sort((Rank[])ranks.toArray(), asc);
//		 
//		 for (int i = 0; i < ranks.length; i++)
//		     System.out.println(array[i]);
		 
		
		
		
	}

	
	
	
	
	
}
