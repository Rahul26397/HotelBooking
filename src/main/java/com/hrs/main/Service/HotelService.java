package com.hrs.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.main.model.Booking;
import com.hrs.main.model.Hotel;
import com.hrs.main.repository.BookingRepository;
import com.hrs.main.repository.HotelRepository;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private HotelRepository hotelRepository;

    public List<String> searchHotels(String destination) {
        // Search hotels by destination (location)
        List<Hotel> hotels = hotelRepository.findByLocation(destination);
        
        // Extract hotel names from the list of Hotel entities
        return extractHotelNames(hotels);
    }

    // Helper method to extract hotel names from a list of Hotel entities
    private List<String> extractHotelNames(List<Hotel> hotels) {
        return hotels.stream()
                .map(Hotel::getName)
                .toList();
    }

    public String createBooking(Booking booking) {
        // Save booking details to the database
        bookingRepository.save(booking);
        return "Booking created for hotel: " + booking.getHotelName();
    }

    public Booking getBookingById(Long bookingId) {
        // Retrieve booking details by ID
        return bookingRepository.findById(bookingId).orElse(null);
    }
}
