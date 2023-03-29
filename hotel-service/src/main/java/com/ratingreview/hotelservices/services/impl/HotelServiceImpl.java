package com.ratingreview.hotelservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingreview.hotelservices.entities.Hotel;
import com.ratingreview.hotelservices.exceptions.ResourceNotFoundException;
import com.ratingreview.hotelservices.payloads.HotelDto;
import com.ratingreview.hotelservices.repositories.HotelRepository;
import com.ratingreview.hotelservices.services.HotelServiceI;

@Service
public class HotelServiceImpl implements HotelServiceI{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HotelRepository hotelRepo;

	@Override
	public HotelDto create(HotelDto hotelDto) {
		Hotel hotel = this.modelMapper.map(hotelDto, Hotel.class);
		Hotel hotel1 = hotelRepo.save(hotel);
		return this.modelMapper.map(hotel1, HotelDto.class);
	
	}

	@Override
	public List<HotelDto> getHotels() {
		List<Hotel> hotels = this.hotelRepo.findAll();
		List<HotelDto> hotelDtos = hotels.stream()
		.map(hotel -> this.modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
		return hotelDtos;
	}

	@Override
	public HotelDto getHotel(String hotelId) {
		Hotel hotel = hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("COULD NOT FIND A HOTEL WITH ID: " + hotelId));
		return this.modelMapper.map(hotel, HotelDto.class);
	}

}
