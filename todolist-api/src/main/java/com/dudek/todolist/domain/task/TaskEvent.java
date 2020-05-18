package com.dudek.todolist.domain.task;

import java.util.Date;

import com.dudek.todolist.domain.Event;

public class TaskEvent implements Event{

	private final Date date;
	
    private final Integer sequenceNumber;
    
    private final TaskEventData eventData;

	public TaskEvent(Date date, Integer sequenceNumber, TaskEventData eventData) {
		this.date = date;
		this.sequenceNumber = sequenceNumber;
		this.eventData = eventData;
	}

	public Date getDate() {
		return date;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public TaskEventData getEventData() {
		return eventData;
	}
}
