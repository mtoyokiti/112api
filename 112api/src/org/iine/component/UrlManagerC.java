package org.iine.component;

import java.util.Random;

import org.iine.Kinds;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class UrlManagerC {

	private static DatastoreService ds = DatastoreServiceFactory
			.getDatastoreService();
	private Entity entity = null;
	private Key key;
	private String password;
	private String title;

	private boolean isFound = false;

	/**
	 * idから作成する（速い）
	 * 
	 * @param id
	 * @throws EntityNotFoundException
	 */
	public UrlManagerC(long id) {
		key = KeyFactory.createKey(Kinds.siteList, id);
		try {
			entity = ds.get(key);
			setFound(true);
		} catch (EntityNotFoundException e) {
			return;
		}

	}

	/**
	 * URLから作成する
	 * 
	 * @param url
	 */
	public UrlManagerC(String url) {
		Query query = new Query(Kinds.siteList);
		query.addFilter("url", FilterOperator.EQUAL, url);
		PreparedQuery pq = ds.prepare(query);
		try {
			entity = pq.asSingleEntity();
		} catch (Exception e) { // エラーの場合
			entity = new Entity(Kinds.siteList);
			entity.setProperty("url", url);
			setPassword(createPassword());
			return;
		}
		if (entity == null) { // 見つからなかった場合
			entity = new Entity(Kinds.siteList);
			entity.setProperty("url", url);
			setPassword(createPassword());
			return;
		} else { // 見つかった場合
			this.key = entity.getKey();
			this.title = (String)entity.getProperty("title");
			this.password = (String)entity.getProperty("password");
			setFound(true);
		}
	}

	/**
	 * @return
	 */
	private String createPassword() {
		// Randomクラスのインスタンス化
		Random rnd = new Random();
		long r = rnd.nextLong();
		return Long.toString(r);
	}

	/**
	 * PasswordをDBに設定する
	 * 
	 * @param pass
	 */
	public void setPassword(String pass) {
		this.password = pass;
		this.entity.setProperty("password", pass);
	}

	public String getPassword() {
		return this.password;
	}

	public String getUrl() {
		return (String) entity.getProperty("url");
	}

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		entity.setProperty("title", title);
	}

	public void write() {
		this.key = ds.put(entity);

	}

	public long getId() {
		return this.key.getId();
	}

	private void setFound(boolean isFound) {
		this.isFound = isFound;
	}

	public boolean isFound() {
		return isFound;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

}
