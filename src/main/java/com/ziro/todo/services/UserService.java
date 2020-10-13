package com.ziro.todo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziro.todo.dtos.RegistrationReq;
import com.ziro.todo.persistence.entities.User;
import com.ziro.todo.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final ObjectMapper mapper;
	private final UserRepository userRepository;

	@Autowired
	public UserService(ObjectMapper mapper, UserRepository userRepository) {
		this.mapper = mapper;
		this.userRepository = userRepository;
	}

	public Long register(RegistrationReq request) {

		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setRole("USER");
		userRepository.save(user);

		return user.getId();
	}

}
