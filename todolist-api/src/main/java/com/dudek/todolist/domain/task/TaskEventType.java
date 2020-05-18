package com.dudek.todolist.domain.task;

import java.util.Arrays;

public enum TaskEventType {
	// name of the event and version
	CREATED("taskCreated/1"),
    COMPLETED("taskCompleted/1");
	
	private final String schema;
	
	private TaskEventType(String schema) {
		this.schema = schema;
	}
	
	public static TaskEventType getTaskEventType(String schema) {
		return Arrays.stream(TaskEventType.values())
	            .filter(enumValue ->
	                enumValue.schema.equalsIgnoreCase(schema))
	            .findFirst()
	            .orElseThrow(() ->
	                new RuntimeException("unknown value: " + schema));
	}
	
	public String getSchema() {
		return this.schema;
	}
}
