package com.ziro.todo.controllers;

import com.ziro.todo.dtos.AuthenticationBean;
import com.ziro.todo.dtos.RegistrationReq;
import com.ziro.todo.persistence.entities.Todo;
import com.ziro.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public Long createTodo(@RequestBody RegistrationReq registrationReq) {
		return userService.register(registrationReq);
	}
	
	@GetMapping(path = "/login")
    public AuthenticationBean authenticate() {
        return new AuthenticationBean("You are authenticated");
    }   

}
