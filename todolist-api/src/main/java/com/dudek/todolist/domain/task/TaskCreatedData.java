package com.dudek.todolist.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskCreatedData extends TaskEventData{

	private final String name;

	@JsonCreator
	public TaskCreatedData(@JsonProperty("name")String name) {
		super(TaskEventType.CREATED);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
