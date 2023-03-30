package com.ratingreview.userservices.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
	
	private String ratingId;
	private String userId;
	private String hotelId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "IST")
	private LocalDate checkInDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "IST")
	private LocalDate checkOutDate;
	private Integer rating;
	private String feedback;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime updatedDateAndTime;
	private HotelDto hotel;
	

}
