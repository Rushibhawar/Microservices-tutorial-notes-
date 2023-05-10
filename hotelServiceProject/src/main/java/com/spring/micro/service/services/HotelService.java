package com.spring.micro.service.services;

import java.util.List;

import com.spring.micro.service.entity.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	List<Hotel> getAllHotels();
	
	Hotel getHotelById(Long hotelId);
}
