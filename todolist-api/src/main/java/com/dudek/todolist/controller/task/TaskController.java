package com.dudek.todolist.controller.task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dudek.todolist.domain.task.Task;
import com.dudek.todolist.domain.task.TaskCompletionException;
import com.dudek.todolist.domain.task.TaskEventService;

@RestController
public class TaskController {

	@Autowired
	private TaskEventService taskEventService;

	@GetMapping(value = "task/{date}")
	List<TaskDto> getTasks(
			@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
		return getTaskEventService()
			.getTasks(date)
			.stream()
			.map(this::mapFrom)
			.collect(Collectors.toList());
	}

	@PutMapping(value = "task/{date}")
	ResponseEntity<String> createTask(
			@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
	        @RequestBody CreateTaskRequestDto requestDto) {
		getTaskEventService().addTask(date, mapFrom(requestDto));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping(value = "task/{date}")
	ResponseEntity<String> completeTask(
			@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
	        @RequestBody CompleteTaskRequestDto requestDto) {
		try {
			getTaskEventService().completeTask(date, mapFrom(requestDto));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(TaskCompletionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	private TaskDto mapFrom(Task task) {
		return new TaskDto(task.getName());
	}
	
	private Task mapFrom(CreateTaskRequestDto requestDto) {
		return new Task(requestDto.getName());
	}
	
	private Task mapFrom(CompleteTaskRequestDto requestDto) {
		return new Task(requestDto.getName());
	}

	protected TaskEventService getTaskEventService() {
		return taskEventService;
	}
}
