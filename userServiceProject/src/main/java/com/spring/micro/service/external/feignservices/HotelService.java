package com.spring.micro.service.external.feignservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.micro.service.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotels/hotel-by-id/{hotelId}")
	Hotel getHotelById(@PathVariable Long hotelId);
}
