package com.hrs.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.main.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

