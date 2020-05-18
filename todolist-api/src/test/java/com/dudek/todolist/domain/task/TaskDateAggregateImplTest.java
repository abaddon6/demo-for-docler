package com.dudek.todolist.domain.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskDateAggregateImplTest {

	@Test
	public void notApplied() {
		TaskDateAggregate taskDateAggregate = new TaskDateAggregateImpl();
		
		assertEquals(0, taskDateAggregate.getTasksToDo().size());
		assertEquals(-1, taskDateAggregate.getSequenceNumber());
	}
	
	@Test
	public void applyEmptyList() {
		List<TaskEvent> emptyList = new ArrayList<>();
		
		TaskDateAggregate taskDateAggregate = new TaskDateAggregateImpl();
		taskDateAggregate.apply(emptyList);
		
		assertEquals(0, taskDateAggregate.getTasksToDo().size());
		assertEquals(1, taskDateAggregate.getSequenceNumber());
	}
	
	@Test
	public void applyListWithCompletedTask() {
		Date currentDate = new Date();
		List<TaskEvent> list = new ArrayList<>();
		list.add(new TaskEvent(currentDate, 1, new TaskCompletedData("my task")));
		
		TaskDateAggregate taskDateAggregate = new TaskDateAggregateImpl();
		taskDateAggregate.apply(list);
		
		assertEquals(0, taskDateAggregate.getTasksToDo().size());
		assertEquals(2, taskDateAggregate.getSequenceNumber());
	}
	
	@Test
	public void applyListWithCreatedTask() {
		Date currentDate = new Date();
		List<TaskEvent> list = new ArrayList<>();
		list.add(new TaskEvent(currentDate, 1, new TaskCreatedData("my task")));
		
		TaskDateAggregate taskDateAggregate = new TaskDateAggregateImpl();
		taskDateAggregate.apply(list);
		
		assertEquals(1, taskDateAggregate.getTasksToDo().size());
		assertEquals(2, taskDateAggregate.getSequenceNumber());
	}
}
