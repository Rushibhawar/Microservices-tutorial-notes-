package com.spring.micro.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceProjectApplication.class, args);
	}

}
