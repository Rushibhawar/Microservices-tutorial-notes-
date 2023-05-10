package com.spring.micro.service.external.feignservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.micro.service.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	//GET
	@GetMapping("/get-all-ratings")
	public ResponseEntity<List<Rating>> getAllRatings();
	
	//POST
	@PostMapping("/ratings/create-rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating ratings);
	
	//PUT
	@PutMapping("/update-rating-by-ratingId/{ratingId}")
	public Rating updateRatingsByRatingId(@PathVariable Long ratingId,@RequestBody Rating rating);
	
	//Delete
	@DeleteMapping("/delete-rating-by-ratingId/{ratingId}")
	public boolean deleteRatingsBYRatingId(@PathVariable Long ratingId);
	
	
}
