package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;

@Mapper(componentModel = "Spring")
@Configuration
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mappings({
	@Mapping(source ="email", target = "emailAdress"),
	@Mapping(source ="role", target = "roleName")
	})
	//User to user DTO
	UserMsDto userToUserMsDto(User user);
	
	//List of Users to List of Use DTOs
	List<UserMsDto> usersToUserMsDtoList(List<User> users);
}
