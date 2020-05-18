package com.dudek.todolist.repository;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EventEntity implements Serializable {

	private static final long serialVersionUID = 3837661143680605631L;

	@EmbeddedId
    private EventEntityPK eventPK;

	private String eventType;
	
	private String eventData;

	public EventEntityPK getEventPK() {
		return eventPK;
	}

	public void setEventPK(EventEntityPK eventPK) {
		this.eventPK = eventPK;
	}
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}
}
