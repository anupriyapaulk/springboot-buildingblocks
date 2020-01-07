package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repository.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="hateoas/users")
@Validated
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping()
	public CollectionModel<User> getAllUsers () {
		List<User> users = userService.getAllUsers();
		
		for (User user : users) {
			//Self Link for users
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);	
			
			//Relationship link for getAllorders
			try {
				CollectionModel<Order> allOrdersCollection = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrdersForUser(userid);
				Link ordersLink = WebMvcLinkBuilder.linkTo(allOrdersCollection).withRel("all-orders");
				user.add(ordersLink);
				
				// Link to individual orders
				List<Order> allOrders= user.getOrders();
				for (Order order : allOrders) {
					Long orderId = order.getOrderId();
					// first approach
					/*Link orderLink= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).slash("orders").slash(orderId).withRel("order-link");					
					order.add(orderLink);*/
					
					EntityModel<Order> orderModel = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getOrderbyOrderId(userid, orderId);
					Link orderLink = WebMvcLinkBuilder.linkTo(orderModel).withRel("order");
					order.add(orderLink);
				}
				
				
				
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		//Self link for getAllusers
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		//CollectionModel is used when entity is a collection
		CollectionModel<User> finalEntityModel = new CollectionModel<User>(users, selfLink);
		return finalEntityModel;
	} 
	
	//Optional is a container object which may or may not contain a non-null value
	@GetMapping("/{id}")
	public EntityModel<User> getUserById( @PathVariable("id")  @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalEntityModel = new EntityModel<User>(user); 
			return finalEntityModel;
		}	catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	

}
