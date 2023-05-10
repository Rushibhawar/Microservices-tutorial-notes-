package com.spring.micro.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.micro.service.entity.Rating;
import com.spring.micro.service.repository.RatingRepository;
import com.spring.micro.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public Rating createRatings(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingById(Long ratingId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByRatingId(ratingId);
	}

	@Override
	public List<Rating> getRatingByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public List<Rating> getRatingByUserId(Long userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public Rating updateRatingsByRatingId(Long ratingId,Rating currentRating) {
		Rating exstingRating = ratingRepository.findRatingByRatingId(ratingId);	
		exstingRating.setHotelId(currentRating.getHotelId());
		exstingRating.setFeedback(currentRating.getFeedback());
		exstingRating.setRating(currentRating.getRating());
		exstingRating.setUserId(currentRating.getUserId());
		
		ratingRepository.save(exstingRating);
		return exstingRating;
	}

}
