package com.spring.micro.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.micro.service.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByRatingId(Long ratingId);
	
	Rating findRatingByRatingId(Long ratinId);
	
	List<Rating> findByUserId(Long userId);
	
	List<Rating> findByHotelId(Long hotelId);
}
