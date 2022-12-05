package com.insert.register.Seat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;
    
    @Override
    public List<Seat> getSeatsFromShowtime(Integer showtimeID) {
        return seatRepository.findAll().stream().filter(a -> a.getShowtime().equals(showtimeID)).collect(Collectors.toList());
    }

    public Seat saveSeat(Seat seat){
        return seatRepository.save(seat);
    }
}
