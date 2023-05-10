package com.spring.micro.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@GetMapping("/get-all-staff")
	public ResponseEntity<List<String>> getAllStaff() {
		List<String> staff = Arrays.asList("Adarsh","Om","Pratik","Alex");
		return ResponseEntity.status(HttpStatus.OK).body(staff);
	}
}
