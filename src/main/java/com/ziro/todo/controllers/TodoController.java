package com.ziro.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziro.todo.dtos.TodoReq;
import com.ziro.todo.persistence.entities.Todo;
import com.ziro.todo.services.TodoService;

@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class TodoController {
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/todo")
	public List<Todo> getTodoList() {
		return todoService.getTodoList();
	}
	
	@GetMapping("/todo/{id}")
	public Todo getTodoById(@PathVariable Long id) {
		return todoService.getTodoById(id);
	}
	
	@DeleteMapping("/todo/{id}")
	public Todo deleteTodoById(@PathVariable Long id) {
		return todoService.deleteId(id);
	}
	
	@PostMapping("/todo")
	public Todo createTodo(@RequestBody TodoReq todoReq) {
		return todoService.createTodo(todoReq);
	}
	
	@PutMapping("/todo/{id}")
	public Todo updateTodo(@PathVariable Long id, @RequestBody TodoReq todoReq) {
		return todoService.updateTodo(id, todoReq.getDescription());
	}
	
	@PutMapping("/todo/switchState/{id}")
	public Todo switchStateTodo(@PathVariable Long id) {
		return todoService.switchState(id);
	}
}
