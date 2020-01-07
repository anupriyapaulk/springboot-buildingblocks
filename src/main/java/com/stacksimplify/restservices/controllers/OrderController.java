package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repository.OrderRepository;
import com.stacksimplify.restservices.repository.UserRepository;

@RestController
@RequestMapping(value="users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping ("/{userid}/orders")
	public List<Order> getAllOrdersForUser(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not found");
		} else {
			return user.get().getOrders();
		}
		
		
	}
	
	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not found");
		} else {
			User user =userOptional.get();
			order.setUser(user);
			orderRepository.save(order);
		}
		
	}
	
	@GetMapping ("/{userid}/orders/{orderid}")
	public Optional<Order> getOrderbyOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not found");
		} else {
			return orderRepository.findById(orderid);
		}
		
		
	}

	
	
	
}
