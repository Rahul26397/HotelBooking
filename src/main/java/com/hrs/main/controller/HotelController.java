package com.hrs.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hrs.main.Service.HotelService;
import com.hrs.main.model.Booking;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<String> searchHotels(@RequestParam("destination") String destination) {
        // Logic to search hotels based on destination
        return hotelService.searchHotels(destination);
    }

    @PostMapping("/bookings")
    public String createBooking(@RequestBody Booking booking) {
        return hotelService.createBooking(booking);
    }

    @GetMapping("/bookings/{bookingId}")
    public Booking getBookingDetails(@PathVariable Long bookingId) {
        return hotelService.getBookingById(bookingId);
    }
}
