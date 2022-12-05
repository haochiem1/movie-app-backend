package com.insert.register.Seat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "seat")
@Transactional
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatID;

    @Column(name="showtime")
    private Integer showtime;

    @Column(name="showroom")
    private Integer showroom;

    @Column(name="seat")
    private String seat;


    public Seat(Integer showtime, Integer showroom, String seat) {
        this.showtime = showtime;
        this.showroom = showroom;
        this.seat = seat;
    }

    public Seat() {}

    public void setSeatID(Integer seatID) {
        this.seatID = seatID;
    }

    public void setShowtime(Integer showtime) {
        this.showtime = showtime;
    }

    public void setShowroom(Integer showroom) {
        this.showroom = showroom;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Integer getSeatID() {
        return seatID;
    }

    public Integer getShowtime() {
        return showtime;
    }

    public Integer getShowroom() {
        return showroom;
    }

    public String getSeat() {
        return seat;
    }
}
