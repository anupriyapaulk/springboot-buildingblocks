package com.stacksimplify.restservices.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserAlreadyExistsException;
import com.stacksimplify.restservices.exception.UserNameNotFoundException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User management Restful service", value="UserController", description = "This is the controller for user services")
@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(tags = "Service for listing all users", value = "get all users")
	@GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers () {
		return userService.getAllUsers();
	} 
	
	@ApiOperation(tags = "Service for listing all users", value = "create users")
	@PostMapping()
	public ResponseEntity<Void> createUser(@ApiParam("User details for the new user to be created") @Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			
		} catch (UserAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
				
	}
	
	
	//Optional is a container object which may or may not contain a non-null value
	@GetMapping("/{id}")
	public Optional<User> getUserById( @PathVariable("id")  @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		}	catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user, @Min(1) @PathVariable("id") Long id) {
		try {
		return userService.updateUserById(user, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/byusername/{name}") 
	public User getUserByUserName(@PathVariable("name") String userName) throws UserNameNotFoundException {
		
		User user =  userService.getUserByUserName(userName);
		
		if (user == null)
			throw new UserNameNotFoundException("UserName "+userName + " not found in user repo");
		else
			return user;
	}
}

