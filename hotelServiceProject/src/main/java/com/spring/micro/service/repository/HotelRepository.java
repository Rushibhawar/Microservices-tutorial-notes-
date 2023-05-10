package com.spring.micro.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.micro.service.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
