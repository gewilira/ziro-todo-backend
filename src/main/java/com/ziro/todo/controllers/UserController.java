package com.ziro.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziro.todo.dtos.AuthenticationBean;
import com.ziro.todo.dtos.RegistrationReq;
import com.ziro.todo.services.UserService;

@RestController
@CrossOrigin
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public Long register(@RequestBody RegistrationReq registrationReq) {
		return userService.register(registrationReq);
	}
	
	@GetMapping(path = "/login")
    public AuthenticationBean authenticate() {
        return new AuthenticationBean("You are authenticated");
    }   

}
