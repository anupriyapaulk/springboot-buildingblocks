package com.stacksimplify.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.User;

// Repository is nothing but the DAO layer
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String userName);

}

