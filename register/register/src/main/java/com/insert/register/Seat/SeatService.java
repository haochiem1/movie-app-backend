package com.insert.register.Seat;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface SeatService {
    public List<Seat> getSeatsFromShowtime(Integer showtimeID);
}
