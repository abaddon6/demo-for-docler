package com.dudek.todolist.controller.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTaskRequestDto {

	@JsonProperty("name")
	private final String name;

	@JsonCreator
	public CreateTaskRequestDto(@JsonProperty("name") String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
