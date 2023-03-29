package com.ratingreview.hotelservices.services;

import java.util.List;

import com.ratingreview.hotelservices.payloads.HotelDto;

public interface HotelServiceI {
	
//	CREATE HOTEL
	HotelDto create(HotelDto hotelDto);
	
//	RETRIEVE ALL HOTEL
	List<HotelDto> getHotels();
	
//	RETRIEVE A HOTEL BY HOTEL ID
	HotelDto getHotel(String hotelId);

}
