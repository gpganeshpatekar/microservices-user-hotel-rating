package com.ratingreview.ratingservices.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ratings")
public class Rating {
	
	@Id
	@Column(name = "ID")
	private String ratingId;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "Hotel_ID")
	private String hotelId;
	@Column(name = "CHECK_IN")
	private LocalDate checkInDate;
	@Column(name = "CHECK_OUT")
	private LocalDate checkOutDate;
	@Column(name = "RATING")
	private Integer rating;
	@Column(name = "FEEDBACK")
	private String feedback;
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDateAndTime;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private LocalDateTime updatedDateAndTime;
	
}

