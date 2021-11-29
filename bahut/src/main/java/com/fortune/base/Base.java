package com.fortune.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class Base {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String id;
	
	@CreationTimestamp
	@Column( updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@UpdateTimestamp
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active = true;

	public Base() {

	}

	/**
	 * @param id
	 * @param createAt
	 * @param updatedAt
	 * @param active
	 */
	public Base(String id, Date createAt, Date updatedAt, boolean active) {
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
