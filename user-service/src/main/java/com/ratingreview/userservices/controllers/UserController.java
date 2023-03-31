package com.ratingreview.userservices.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ratingreview.userservices.entities.User;
import com.ratingreview.userservices.payloads.ApiResponse;
import com.ratingreview.userservices.payloads.UserDto;
import com.ratingreview.userservices.services.UserServiceI;
import com.ratingreview.userservices.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.Builder;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServiceI userService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
//	TO CREATE A NEW USER
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
		String randomUserId = UUID.randomUUID().toString();
		userDto.setUserId(randomUserId);
		userDto.setActive(true);
		UserDto save = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(save,HttpStatus.CREATED);
	}
	
//	TO GET ALL USERS
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	int retryCount = 1;
//  TO GET A USER BY USER ID
	@GetMapping(value = "/{userId}",produces = "application/json")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack" )
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
		logger.info("Get Single User Hanlder : UserController");
		logger.info("Retry Count: {}",retryCount);
		UserDto user = this.userService.getUserById(userId);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	
//	CREATING FALL BACK METHOD FOR CIRCUIT BREAKER
	public ResponseEntity<UserDto> ratingHotelFallBack(String userId, Exception ex){
		
//		logger.info("Fallback is executed because service is down : ",ex.getMessage());
		UserDto userDto = new UserDto();
		
		userDto.setName("Dummy");
		userDto.setGender("M");
		userDto.setAge(18);
		userDto.setAbout("I am Dummy I am here because some services are down");
		userDto.setEmail("dummy@gmail.com");
		userDto.setMobile("9000012345");
		userDto.setPassword("Dummy123#");
		userDto.setActive(true);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
//	TO GET A USER BY USER EMAIL
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping(value = "email/{email}",produces = "application/json")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
		UserDto user = this.userService.getByEmail(email);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
//	TO UPDATE A USER 
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@PutMapping (value = "/{userId}",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") String userId,@RequestBody UserDto userDto){
		UserDto update = this.userService.updateUser(userId, userDto);
		return new ResponseEntity<UserDto>(update,HttpStatus.OK);
	}
	
//	 TO DELET A USER
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("USER WITH USER ID "+userId+" DELETED SUCCESSFULLY ",true),HttpStatus.OK);
	}
	
//	 TO RE-ACTIVATE A USER
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@PutMapping(value = "activate/{userId}")
	public ResponseEntity<ApiResponse> activateUser(@PathVariable String userId){
		this.userService.activateUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("USER WITH USER ID "+userId+" RE-ACTIVATED SUCCESSFULLY ",true),HttpStatus.OK);
	}
	
//	 TO DEACTIVATE A USER
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@PutMapping(value = "deactivate/{userId}")
	public ResponseEntity<ApiResponse> deactivateUser(@PathVariable String userId){
		this.userService.deactivateUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("USER WITH USER ID "+userId+" DE-ACTIVATED SUCCESSFULLY ",true),HttpStatus.OK);
	}
}
