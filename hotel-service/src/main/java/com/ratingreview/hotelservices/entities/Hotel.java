package com.ratingreview.hotelservices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	
	@Id
	@Column(name = "ID")
	private String hotelId;
	private String name;
	private String location;
	private String about;
	private double startsFrom;
	@Column(name = "average_rating")
	private double averageRating;
	

}
