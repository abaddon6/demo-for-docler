package com.dudek.todolist.controller.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDto {

	@JsonProperty("name")
	private final String name;

	@JsonCreator
	public TaskDto(@JsonProperty("name") String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
