package com.dudek.todolist.domain.task;

import java.util.List;

import com.dudek.todolist.domain.EventAggregate;

public interface TaskDateAggregate extends EventAggregate<TaskEvent> {

	public List<Task> getTasksToDo();
}
