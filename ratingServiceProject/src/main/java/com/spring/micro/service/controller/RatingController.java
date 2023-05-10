package com.spring.micro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.micro.service.entity.Rating;
import com.spring.micro.service.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/")
	public String ratings() {
		return "ratings";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create-rating")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRatings(rating));
	}
	
	//Update ratings
	@PutMapping("/update-rating-by-ratingId/{ratingId}")
	public ResponseEntity<Rating> updateRatingByRatingId(@PathVariable Long ratingId,@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.updateRatingsByRatingId(ratingId, rating));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/get-all-ratings")
	public ResponseEntity<List<Rating>> getAllRatings(){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
	}
	
	@GetMapping("/get-ratings-by-ratingId/{ratingId}")
	public ResponseEntity<List<Rating>> getRatingsByRatingId(@PathVariable Long ratingId ){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingById(ratingId));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable Long hotelId ){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId ){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
	}
}
