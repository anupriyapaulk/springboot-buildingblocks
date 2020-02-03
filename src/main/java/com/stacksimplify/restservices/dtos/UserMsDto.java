package com.stacksimplify.restservices.dtos;

public class UserMsDto {
	
	private Long id;
	
	private String username;
	
	private String emailAdress;
	private String roleName;

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

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public UserMsDto(Long id, String username, String emailAdress, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.emailAdress = emailAdress;
		this.roleName=roleName;
	}

	public UserMsDto() {

	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	

}
