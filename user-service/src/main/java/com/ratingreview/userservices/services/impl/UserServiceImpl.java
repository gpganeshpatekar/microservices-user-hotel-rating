package com.ratingreview.userservices.services.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ratingreview.userservices.entities.Rating;
import com.ratingreview.userservices.entities.User;
import com.ratingreview.userservices.exceptions.ResourceNotFoundException;
import com.ratingreview.userservices.payloads.HotelDto;
import com.ratingreview.userservices.payloads.RatingDto;
import com.ratingreview.userservices.payloads.UserDto;
import com.ratingreview.userservices.repositories.UserRepository;
import com.ratingreview.userservices.services.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

		RatingDto[] ratingOfUser = this.restTemplate
				.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), RatingDto[].class);

		List<RatingDto> ratings = Arrays.stream(ratingOfUser).toList();

		ratings.stream().map(rating -> {

			HotelDto hotelDto = restTemplate.getForObject("http://localhost:8082/hotels/" + rating.getHotelId(), HotelDto.class);
			rating.setHotel(hotelDto);

			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratings);

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
