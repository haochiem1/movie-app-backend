package com.insert.register.Booking;

import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    
    public Booking saveBooking(Booking booking);

}
