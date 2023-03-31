package com.ratingreview.hotelservices.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingreview.hotelservices.payloads.HotelDto;
import com.ratingreview.hotelservices.services.HotelServiceI;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelServiceI hotelService;
	
	
//	CREATE
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
		String randomHotelId = UUID.randomUUID().toString();
		hotelDto.setHotelId(randomHotelId);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotelDto));
	}
	
//	GET ALL HOTEL
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin') || hasAuthority('Normal')")
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<HotelDto>> getAllHotel(){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotels());
	}
	
//	GET HOTEL
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping(value = "/{hotelId}",produces = "application/json")
	public ResponseEntity<HotelDto> getHotel(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
	}

}
