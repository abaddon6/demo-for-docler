package com.dudek.todolist.domain.task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudek.todolist.repository.EventEntityPK;
import com.dudek.todolist.repository.task.TaskEventEntity;
import com.dudek.todolist.repository.task.TaskEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TaskEventServiceImpl implements TaskEventService{

	private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private TaskEventRepository taskEventRepository;

	@Override
	public List<Task> getTasks(Date date) {
		List<TaskEvent> taskEvents = getTaskEventRepository()
			.findByDate(date)
			.stream()
			.map(this::mapFrom)
			.collect(Collectors.toList());
		
		TaskDateAggregate dateAggregate = new TaskDateAggregateImpl();
		dateAggregate.apply(taskEvents);
		return dateAggregate.getTasksToDo();
	}
	
	@Override
	public void addTask(Date date, Task task) {
		List<TaskEvent> taskEvents = getTaskEventRepository()
				.findByDate(date)
				.stream()
				.map(this::mapFrom)
				.collect(Collectors.toList());
			
		TaskDateAggregate dateAggregate = new TaskDateAggregateImpl();
		dateAggregate.apply(taskEvents);
		getTaskEventRepository().save(mapFrom(mapFrom(date, task, TaskEventType.CREATED,
				dateAggregate.getSequenceNumber())));
	}
	
	@Override
	public void completeTask(Date date, Task task) throws TaskCompletionException{
		List<TaskEvent> taskEvents = getTaskEventRepository()
				.findByDate(date)
				.stream()
				.map(this::mapFrom)
				.collect(Collectors.toList());
			
		TaskDateAggregate dateAggregate = new TaskDateAggregateImpl();
		dateAggregate.apply(taskEvents);
		boolean exists = dateAggregate.getTasksToDo()
				.stream()
				.anyMatch(t -> t.getName().equals(task.getName()));
		if(exists) {
			getTaskEventRepository().save(mapFrom(mapFrom(date, task, TaskEventType.COMPLETED, 
					dateAggregate.getSequenceNumber())));
		}
		else {
			throw new TaskCompletionException(
					String.format("Task ['%s'] already completed", task.getName()));
		}
	}
	
	private TaskEvent mapFrom(TaskEventEntity taskEntity) {
		TaskEventType eventType = TaskEventType.getTaskEventType(taskEntity.getEventType());
		TaskEventData taskEventData = null;

		try {
			switch(eventType) {
				case COMPLETED: {
					taskEventData = mapper.readValue(taskEntity.getEventData(),
							TaskCompletedData.class);
					break;
				}
				case CREATED: {
					taskEventData = mapper.readValue(taskEntity.getEventData(),
							TaskCreatedData.class);
					break;
				}
				default:
					throw new RuntimeException("Wrong event type: " + eventType);
			}
		}
		catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return new TaskEvent(
				taskEntity.getEventPK().getDate(),
				taskEntity.getEventPK().getId(),
				taskEventData);
	}
	
	private TaskEvent mapFrom(Date date, Task task, TaskEventType taskEventType, Integer sequenceNumber) {
		TaskEventData taskEventData  = null;
		switch(taskEventType) {
			case COMPLETED: {
				taskEventData = new TaskCompletedData(task.getName());
				break;
			}
			case CREATED: {
				taskEventData = new TaskCreatedData(task.getName());
				break;
			}
			default:
				throw new RuntimeException("Wrong event type: " + taskEventType);
		}
		return new TaskEvent(date, sequenceNumber, taskEventData);
	}
	
	private TaskEventEntity mapFrom(TaskEvent taskEvent) {
		TaskEventEntity taskEventEntity = new TaskEventEntity();
		taskEventEntity.setEventPK(
				new EventEntityPK(taskEvent.getSequenceNumber(), taskEvent.getDate()));
		taskEventEntity.setEventType(taskEvent.getEventData().getTaskEventType().getSchema());
		try {
			taskEventEntity.setEventData(mapper.writeValueAsString(taskEvent.getEventData()));
		} 
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return taskEventEntity;
	}
	
	protected TaskEventRepository getTaskEventRepository() {
		return taskEventRepository;
	}
}
