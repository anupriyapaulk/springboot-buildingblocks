package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserAlreadyExistsException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repository.UserRepository;

// To follow the standard design pattern to have all the business login
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserAlreadyExistsException {
		// Check if user exists with username then throw exception
		if (userRepository.findByUsername(user.getUsername()) !=null) {
			throw new UserAlreadyExistsException("User Already exists");
		} else {
			return userRepository.save(user);
		}
		
					
	}
	
	//Optional is a container object which may or may not contain a non-null value
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in User Repository");
		}
		return user;
	}
	
	public User updateUserById(User user, Long id) throws UserNotFoundException {
		
		Optional<User> checkUser = userRepository.findById(id);
		if(!checkUser.isPresent()) {
			throw new UserNotFoundException("User not found in User Repository, Provide correct user");
		}
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById( Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in User Repository, Provide correct user");
		}
	}
	
	public User getUserByUserName( String userName) {
		return userRepository.findByUsername(userName);
	} 
	
}
