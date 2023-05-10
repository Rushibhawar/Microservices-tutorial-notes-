package com.spring.micro.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

	private Long ratingId;
	private Long userId;
	private Long hotelId;
	private int rating;
	private String feedback;
	
	private Hotel hotel;
}
