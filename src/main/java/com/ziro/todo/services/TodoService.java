package com.ziro.todo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ziro.todo.dtos.TodoReq;
import com.ziro.todo.persistence.entities.Todo;
import com.ziro.todo.persistence.repositories.TodoRepository;

@Service
public class TodoService {
	
	private final TodoRepository todoRepository;
	
	@Autowired
	protected TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> getTodoList() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return todoRepository.findByCreatedByAndDeleteDateIsNull(((User)authentication.getPrincipal()).getUsername());
	}

	public Todo getTodoById(Long id) {
		return todoRepository.getOne(id);
	}
	
	public Todo deleteId(Long id) {
		Todo todo = todoRepository.getOne(id);
		todo.setDeleteDate(new Date());
		return todoRepository.save(todo);
	}
	
	public Todo switchState(Long id) {
		Todo todo = todoRepository.getOne(id);
		if(todo.getCompletedDate() != null) {
			todo.setCompletedDate(null);
		} else {
			todo.setCompletedDate(new Date());
		}

		return todoRepository.save(todo);
	}
	
	public Todo createTodo(TodoReq todoReq) {
		Todo todo = new Todo();
		todo.setDescription(todoReq.getDescription());
		return todoRepository.save(todo);
	}
	
	public Todo updateTodo(Long id, String description) {
		Todo todo = todoRepository.getOne(id);
		todo.setDescription(description);
		return todoRepository.save(todo);
	}
}
