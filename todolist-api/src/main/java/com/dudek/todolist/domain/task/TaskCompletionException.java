package com.dudek.todolist.domain.task;

public class TaskCompletionException extends RuntimeException {

	private static final long serialVersionUID = 5462356405861877751L;

	public TaskCompletionException(String reason) {
		super(reason);
	}
}
