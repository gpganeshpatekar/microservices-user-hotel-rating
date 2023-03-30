package com.ratingreview.userservices.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ratingreview.userservices.payloads.RatingDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Column(name = "ID")
	private String userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "AGE")
	private Integer age;
	@Column(name = "ABOUT")
	private String about;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "MOBILE")
	private String mobile;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "ACTIVE")
	private boolean active;
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDateAndTime;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private LocalDateTime updatedDateAndTime;
	
	@Transient
	private List<RatingDto> ratings = new ArrayList<>();
	
	
}
