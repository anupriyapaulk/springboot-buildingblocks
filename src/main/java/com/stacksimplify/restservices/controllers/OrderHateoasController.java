package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.engine.spi.CollectionEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repository.OrderRepository;
import com.stacksimplify.restservices.repository.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="hateoas/users")

public class OrderHateoasController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	

	@GetMapping ("/{userid}/orders")
	public CollectionModel<Order> getAllOrdersForUser(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not found");
		} else {
			List<Order> allOrders=  user.get().getOrders();

			CollectionModel<Order> collection= new CollectionModel<Order>(allOrders);
			return collection;
		}
		
		
	}
	
	@GetMapping ("/{userid}/orders/{orderid}")
	public EntityModel<Order> getOrderbyOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not found");
		} else {
			Optional<Order> optionalOrder = orderRepository.findById(orderid);
			EntityModel<Order> model = new EntityModel<Order>(optionalOrder.get());
			return model;
		}

	}

}
	
