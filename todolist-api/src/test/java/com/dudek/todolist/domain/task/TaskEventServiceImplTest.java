package com.dudek.todolist.domain.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskEventServiceImplTest {

	@Autowired
	private TaskEventService taskEventService;
	
	private static Date date;
	
	@BeforeAll
	public static void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		date = cal.getTime();
	}
	
	@Test
	public void getTasks() {
		List<Task> tasks = taskEventService.getTasks(date);
		
		assertEquals(1, tasks.size());
	}
	
	@Test
	public void addTask() {
		taskEventService.addTask(date, new Task("new task"));
		List<Task> tasks = taskEventService.getTasks(date);
		
		assertEquals(2, tasks.size());
		assertEquals("new task", tasks.get(1).getName());
	}
	
	@Test
	public void completeTask() {
		taskEventService.completeTask(date, new Task("Next task to do"));
		List<Task> tasks = taskEventService.getTasks(date);
		
		assertEquals(0, tasks.size());
	}
}
