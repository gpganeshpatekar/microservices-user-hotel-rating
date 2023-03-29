package com.ratingreview.hotelservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratingreview.hotelservices.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
	
	Optional<Hotel> findByHotelId(Integer hotelId);

}
