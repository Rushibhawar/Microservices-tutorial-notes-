package com.spring.micro.service.services;

import java.util.List;

import com.spring.micro.service.entity.Rating;

public interface RatingService {

	Rating createRatings(Rating rating);
	
	Rating updateRatingsByRatingId(Long ratingId,Rating rating);
	
	List<Rating> getAllRatings();
	
	List<Rating> getRatingById(Long ratingId);
	
	List<Rating> getRatingByHotelId(Long hotelId);
	
	List<Rating> getRatingByUserId(Long userId);
}
