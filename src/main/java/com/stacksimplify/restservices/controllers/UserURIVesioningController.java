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

import com.stacksimplify.restservices.dtos.UserDTOV1;
import com.stacksimplify.restservices.dtos.UserDTOV2;
import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/versioning/uri/users")
public class UserURIVesioningController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// URI based versioning - V1
	@GetMapping({"/v1.0/{id}","/v1.1/{id}" })
	public UserDTOV1 getUserById( @PathVariable("id")  @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not found");
			
		} 
		
		User user = userOptional.get();
		// If there is any ambiguity
		//modelMapper.getConfiguration().setAmbiguityIgnored(true);	
		UserDTOV1 userDtoV1 = modelMapper.map(user, UserDTOV1.class);
		return userDtoV1;
	}
	
	// URI based versioning - V2
	@GetMapping("/v2.0/{id}")
	public UserDTOV2 getUserByIdV2( @PathVariable("id")  @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not found");
			
		} 
		
		User user = userOptional.get();
		// If there is any ambiguity
		//modelMapper.getConfiguration().setAmbiguityIgnored(true);	
		UserDTOV2 userDtoV2 = modelMapper.map(user, UserDTOV2.class);
		return userDtoV2;
	}
}
