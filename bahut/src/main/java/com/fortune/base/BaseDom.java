package com.fortune.base;

import java.util.Date;

public class BaseDom {

	private String id;
	private Date createAt;
	private Date updatedAt;
	private boolean active = true;
	
	public BaseDom() {
		
	}

	/**
	 * @param id
	 * @param createAt
	 * @param updatedAt
	 * @param active
	 */
	public BaseDom(String id, Date createAt, Date updatedAt, boolean active) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.updatedAt = updatedAt;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
