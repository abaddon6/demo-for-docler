package com.dudek.todolist.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskCompletedData extends TaskEventData{

	private final String name;

	@JsonCreator
	public TaskCompletedData(@JsonProperty("name")String name) {
		super(TaskEventType.COMPLETED);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
