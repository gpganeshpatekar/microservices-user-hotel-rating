package com.ratingreview.ratingservices.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingreview.ratingservices.payloads.RatingDto;
import com.ratingreview.ratingservices.services.RatingServiceI;


@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingServiceI ratingService;
	
//	CREATE RATING
	@PostMapping("/")
	public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto){
		String randomRatingId = UUID.randomUUID().toString();
		ratingDto.setRatingId(randomRatingId);
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(ratingDto));
	}
	
//	GET ALL RATINGS
	@GetMapping("/")
	public ResponseEntity<List<RatingDto>> getAllRatings(){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings()); 
	}
	
//	GET ALL RATINGS BY USER 
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<RatingDto>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingsByUserId(userId));
	}
	
//	GET ALL RATINGS BY HOTEL
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<RatingDto>> getRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingByHotelId(hotelId));
	}
	
	

}
