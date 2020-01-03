package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repository.UserRepository;

// To follow the standard design pattern to have all the business login
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);			
	}
	
	//Optional is a container object which may or may not contain a non-null value
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	public User updateUserById(User user, Long id) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById( Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName( String userName) {
		return userRepository.findByUsername(userName);
	} 
	
}
