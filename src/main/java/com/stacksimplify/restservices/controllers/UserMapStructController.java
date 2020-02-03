package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repository.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping
	List<UserMsDto> getAllUserDtos() {
		return userMapper.usersToUserMsDtoList(userRepo.findAll());
	} 
	
	@GetMapping("/{id}") 
	UserMsDto getUserByUserID(@PathVariable Long id) { 
		Optional<User> userOptional = userRepo.findById(id);
		User user = userOptional.get();
		return userMapper.userToUserMsDto(user); 
	}
	 
	

}
