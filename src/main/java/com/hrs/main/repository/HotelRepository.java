package com.hrs.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.main.model.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
}

