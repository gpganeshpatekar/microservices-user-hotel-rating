package com.ratingreview.userservices.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ratingreview.userservices.payloads.HotelDto;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	public HotelDto getHotel(@PathVariable("hotelId") String hotelId);
	

}
