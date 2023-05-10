package com.spring.micro.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.micro.service.entity.Hotel;
import com.spring.micro.service.exception.ExceptionClass;
import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
import com.spring.micro.service.repository.HotelRepository;
import com.spring.micro.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(Long hotelId) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(hotelId).orElseThrow(() -> new ExceptionClass().new ResourceNotFoundException("No User found with this credentials"));
	}


}
