package com.dudek.todolist.domain.task;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDateAggregateImpl implements TaskDateAggregate{

    private final HashMap<String, Task> todoTasks;
    private int sequenceNumber;

    public TaskDateAggregateImpl() {
        this.todoTasks = new LinkedHashMap<>();
        this.sequenceNumber = -1;
    }
    
    @Override
    public void apply(List<TaskEvent> events) {
    	if(events.isEmpty()) {
    		sequenceNumber = 1;
    	}
    	else {
    		sequenceNumber = events.get(events.size()-1)
	    			.getSequenceNumber() + 1;
    	}
    	events
    		.stream()
    		.forEach(this::addTask);
    }
    
    @Override
    public List<Task> getTasksToDo() {
    	return todoTasks
    		.values()
    		.stream()
    		.collect(Collectors.toList());
    }
    
    @Override
    public int getSequenceNumber() {
    	return sequenceNumber;
    }
    
    private void addTask(TaskEvent taskEvent) {
    	if(TaskEventType.CREATED.equals(taskEvent.getEventData().getTaskEventType())) {
    		TaskCreatedData taskCreated = (TaskCreatedData) taskEvent.getEventData();
    		todoTasks.put(taskCreated.getName(), new Task(taskCreated.getName()));
    	}
    	else if(TaskEventType.COMPLETED.equals(taskEvent.getEventData().getTaskEventType())) {
    		TaskCompletedData taskCompleted = (TaskCompletedData) taskEvent.getEventData();
    		todoTasks.remove(taskCompleted.getName());
    	}
    }
}
