package com.dudek.todolist.domain.task;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class TaskEventData {

	private final TaskEventType taskEventType;

	public TaskEventData(TaskEventType taskEventType) {
		this.taskEventType = taskEventType;
	}

	@JsonIgnore
	public TaskEventType getTaskEventType() {
		return taskEventType;
	}
}
