package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/modelmapper/users")
public class UserModelMapperController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//Optional is a container object which may or may not contain a non-null value
	@GetMapping("/{id}")
	public UserMmDto getUserById( @PathVariable("id")  @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not found");
			
		} 
		
		User user = userOptional.get();
		// If there is any ambiguity
		//modelMapper.getConfiguration().setAmbiguityIgnored(true);	
		UserMmDto userDto = modelMapper.map(user, UserMmDto.class);
		return userDto;
	}
}
