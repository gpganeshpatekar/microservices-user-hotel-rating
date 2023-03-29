package com.ratingreview.userservices.services;

import java.util.List;

import com.ratingreview.userservices.payloads.UserDto;

public interface UserServiceI {
	
//	Create
	UserDto createUser(UserDto userDto);
//	Retrieve All User
	List<UserDto> getAllUsers();
//	Retrieve User By ID
	UserDto getUserById(String userId);
//	Retrieve User By Email
	UserDto getByEmail(String email);
//	Update User
	UserDto updateUser(String userId, UserDto UserDto);
//	Delete User
	void deleteUser(String userId);
//	Reactivate User
	void activateUser(String userId);
//	Deactivate User
	void deactivateUser(String userId);
	

}
