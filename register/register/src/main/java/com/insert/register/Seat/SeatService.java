package com.insert.register.Seat;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SeatService {
    public List<Seat> getSeatsFromShowtime(Integer showtimeID);
    public Seat saveSeat(Seat seat);
}
