package com.ratingreview.userservices.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
	
	private String hotelId;
	private String name;
	private String location;
	private String about;
	private double startsFrom;
	private double averageRating;

}
