package com.spring.micro.service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.micro.service.entity.User;
import com.spring.micro.service.exception.ExceptionClass.ResourceNotFoundException;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUserById(Long userId)throws ResourceNotFoundException ;
	
	void deleteUserById(Long userId);
	
	void updateUserById(Long userId);
}
