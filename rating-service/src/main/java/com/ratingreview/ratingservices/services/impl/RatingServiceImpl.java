package com.ratingreview.ratingservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingreview.ratingservices.entities.Rating;
import com.ratingreview.ratingservices.payloads.RatingDto;
import com.ratingreview.ratingservices.repositories.RatingRepository;
import com.ratingreview.ratingservices.services.RatingServiceI;

@Service
public class RatingServiceImpl implements RatingServiceI {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RatingRepository ratingRepo;

	@Override
	public RatingDto createRating(RatingDto ratingDto) {
		Rating ratings = this.modelMapper.map(ratingDto, Rating.class);
		Rating ratings1 = ratingRepo.save(ratings);
		return this.modelMapper.map(ratings1, RatingDto.class);
	}

	@Override
	public List<RatingDto> getRatings() {
		List<Rating> ratings = this.ratingRepo.findAll();
		List<RatingDto> ratingDtos = ratings.stream()
		.map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return ratingDtos;
	}

	@Override
	public List<RatingDto> getAllRatingsByUserId(String userId) {
		List<Rating> ratingsByUserId = ratingRepo.findByUserId(userId);
		List<RatingDto> ratingsDtoByUserId = ratingsByUserId.stream()
				.map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return ratingsDtoByUserId;
	}

	@Override
	public List<RatingDto> getAllRatingByHotelId(String hotelId) {
		List<Rating> ratingsByHotelId = ratingRepo.findByHotelId(hotelId);
		List<RatingDto> ratingDtosByHotelId = ratingsByHotelId.stream()
				.map(rating -> this.modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		return ratingDtosByHotelId;
	}

}
