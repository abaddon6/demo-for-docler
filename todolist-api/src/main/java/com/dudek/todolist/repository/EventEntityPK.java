package com.dudek.todolist.repository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EventEntityPK implements Serializable{

	private static final long serialVersionUID = 8761987271709365812L;

	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	public EventEntityPK() {}

	public EventEntityPK(Integer id, Date date) {
		this.id = id;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
