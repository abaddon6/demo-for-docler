package com.dudek.todolist.domain.task;

import java.util.Date;
import java.util.List;

public interface TaskEventService {

	public List<Task> getTasks(Date date);
	
	public void addTask(Date date, Task task);
	
	public void completeTask(Date date, Task task) throws TaskCompletionException;
}
