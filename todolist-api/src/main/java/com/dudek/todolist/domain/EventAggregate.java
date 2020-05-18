package com.dudek.todolist.domain;

import java.util.List;

public interface EventAggregate<EVENT extends Event> {
	
	public void apply(List<EVENT> events);
	
	public int getSequenceNumber();
}
