package com.ratingreview.ratingservices.services;

import java.util.List;

import com.ratingreview.ratingservices.payloads.RatingDto;

public interface RatingServiceI {
	
//	create
	RatingDto createRating(RatingDto ratingDto);
	
//	get all ratings
	List<RatingDto> getRatings();
	
//	get all by user id
	List<RatingDto > getAllRatingsByUserId(String userId);
	
//	get all by hotel
	List<RatingDto> getAllRatingByHotelId(String hotelId);

}
