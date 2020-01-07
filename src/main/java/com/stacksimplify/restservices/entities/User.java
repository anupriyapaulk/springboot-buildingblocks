package com.stacksimplify.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//Entity
@Entity // use name property to change the default entity name which is the class name
@Table(name="user") // if the table name to be differnt from entity name, also schema property can be used if multiple schemas are configured
public class User {
	@Id() //With this annotation JPA makes this feild as Primarykey
	@GeneratedValue // TO auto-generate
	private Long id;
	
	@Column(name="USER_NAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message="Username is manadtory")
	private String username;
	
	@Column(name="FIRST_NAME", length = 50, nullable = false)
	@Size(min=2, message="Firstname should be more than two characters")
	private String firstName;
	
	@Column(name="LAST_NAME", length = 50, nullable = false)
	private String lastName;
	
	@Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
	private String email;
	
	@Column(name="ROLE", length = 50, nullable = false)
	private String role;
	
	@Column(name="SSN", length = 50, nullable = false, unique = true)
	private String ssn;

	// No argument constructor is mandatory for JPA
	public User() {

	}

	public User(Long id, String username, String firstName, String lastName, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	// Optional required for bean logging and troubleshooting
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
}
