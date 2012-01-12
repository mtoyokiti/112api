package org.iine.page;

import com.google.appengine.api.datastore.Entity;

public class Rank {
	private int count;
	private Entity entity;
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Entity getEntity() {
		return entity;
	}
	
}
