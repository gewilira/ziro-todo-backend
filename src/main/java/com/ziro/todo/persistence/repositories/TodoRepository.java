package com.ziro.todo.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ziro.todo.persistence.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	List<Todo> findByCompletedDateIsNotNull();
	
	List<Todo> findByDeleteDateIsNotNull();
	
	List<Todo> findByDeleteDateIsNull();

	List<Todo> findByCreatedByAndDeleteDateIsNull(String createdBy);
	
}
