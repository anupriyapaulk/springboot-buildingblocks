package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.hateoas.*;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//Entity
@ApiModel(description = "This modal is to create a user")
@Entity // use name property to change the default entity name which is the class name
@Table(name="user") // if the table name to be different from entity name, also schema property can be used if multiple schemas are configured
//@JsonIgnoreProperties({"firstName" ,  "lastName"}) //Static filtering by adding to fields to be ingonred by Jackson during serialization
//@JsonFilter(value="userFilter") //Used for MappingJacksonValue filtering
public class User extends RepresentationModel<User> {
	@ApiModelProperty(notes = "Auto generated unique id", required = true, position = 1)
	@Id() //With this annotation JPA makes this feild as Primarykey
	@GeneratedValue // TO auto-generate
	@JsonView(Views.External.class)
	private Long id;
	
	@ApiModelProperty(notes = "User name", example="abc123" , position = 2)
	@Size(min=2, max=50, message="Username should be more than two characters")
	@Column(name="USER_NAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message="Username is manadtory")
	@JsonView(Views.External.class)
	private String username;
	
	@ApiModelProperty(notes = "First name",  position = 3)
	@Column(name="FIRST_NAME", length = 50, nullable = false)
	@Size(min=2, max=50, message="Firstname should be more than two characters")
	@JsonView(Views.External.class)
	private String firstName;
	
	@ApiModelProperty(notes = "First name", position = 2)
	@Column(name="LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastName;
	
	@Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name="ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name="SSN", length = 50, nullable = false, unique = true)
	@JsonView(Views.Internal.class)
	//@JsonIgnore //This will ingnore this feild during Jackson parsing, it may lead to error during post or put as it is not a nullable field
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name="ADDRESS", length = 200, nullable = true)
	@JsonView(Views.Internal.class)
	private String address;
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	// No argument constructor is mandatory for JPA
	public User() {

	}

	public User(Long id, @NotEmpty(message = "Username is manadtory") String username,
			@Size(min = 2, message = "Firstname should be more than two characters") String firstName, String lastName,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
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
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	// Optional required for bean logging and troubleshooting
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address
				+ "]";
	}
	
}
