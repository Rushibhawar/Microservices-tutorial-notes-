package com.spring.micro.service.entity;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	
	private Long hotelId;
	private String hotelName;
	private String hotelAbout;
	private String hotelLocation;
}
