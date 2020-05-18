package com.dudek.todolist.repository.task;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dudek.todolist.repository.EventEntityPK;

public interface TaskEventRepository extends JpaRepository<TaskEventEntity, EventEntityPK> {
	
	@Query("SELECT taskEvent " 
		     + "FROM TaskEvent taskEvent "
		     + "WHERE taskEvent.eventPK.date = :date "
		     + "ORDER BY taskEvent.eventPK.id ")
	List<TaskEventEntity> findByDate(@Param("date")Date date);
}
