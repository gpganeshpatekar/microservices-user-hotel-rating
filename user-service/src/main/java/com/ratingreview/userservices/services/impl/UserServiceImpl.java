package com.ratingreview.userservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingreview.userservices.entities.User;
import com.ratingreview.userservices.exceptions.ResourceNotFoundException;
import com.ratingreview.userservices.payloads.UserDto;
import com.ratingreview.userservices.repositories.UserRepository;
import com.ratingreview.userservices.services.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User save = this.userRepository.save(user);
		return this.modelMapper.map(save, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH ID : " + userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	public UserDto getByEmail(String email) {
		User user = this.userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH EMAIL : " + email));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH ID : " + userId));
		user.setName(userDto.getName());
		user.setGender(userDto.getGender());
		user.setAge(userDto.getAge());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		user.setPassword(userDto.getPassword());
		user.setActive(userDto.isActive());
		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH ID : " + userId));
		this.userRepository.delete(user);

	}

	@Override
	public void activateUser(String userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH ID : " + userId));
		user.setActive(true);
	}

	@Override
	public void deactivateUser(String userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A USER WITH ID : " + userId));
		user.setActive(false);
	}

}
