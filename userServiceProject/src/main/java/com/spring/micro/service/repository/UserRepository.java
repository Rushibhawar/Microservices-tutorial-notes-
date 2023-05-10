package com.spring.micro.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.micro.service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
}
