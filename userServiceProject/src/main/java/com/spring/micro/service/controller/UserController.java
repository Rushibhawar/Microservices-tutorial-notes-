package com.spring.micro.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.micro.service.entity.User;
import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;
import com.spring.micro.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public String usersa() {
		System.out.println("ok");
		return "ok";
	}
	
	@PostMapping("/create-user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	int retryCount = 1;
	
	@GetMapping("/get-users/{userId}")
	//@Retry(name = "RATING-HOTEL-RETRY-SERVICE", fallbackMethod = "ratingFallback")
	//@CircuitBreaker(name = "RATING-HOTEL-BREAKER", fallbackMethod = "ratingFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) throws ResourceNotFoundException {
		logger.info("retrycount : {}", retryCount);
		retryCount++;
		User user = userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	//Creating rating fallback method
	public ResponseEntity<User> ratingFallback(@PathVariable Long userId, Exception exp) {
		exp.printStackTrace();
		logger.info("Fallback method is executed due to the service has failed : ",exp.getMessage());
		
		User user = User.builder()
						.userName("Dummy Name")
						.userEmail("dummy@gmail.com")
						.userAbout("Thid is a dummy value as the service is down")
						.build();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
}
