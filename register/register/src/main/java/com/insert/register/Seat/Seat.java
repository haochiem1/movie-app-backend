package com.insert.register.Seat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatID;

    @Column(name="showtime")
    private Integer showtime;

    @Column(name="showroom")
    private Integer showroom;

    @Column(name="row")
    private String row;

    @Column(name="rowNum")
    private Integer rowNum;

    public Seat(Integer showtime, Integer showroom, String row, Integer rowNum) {
        this.showtime = showtime;
        this.showroom = showroom;
        this.row = row;
        this.rowNum = rowNum;
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

    public void setRow(String row) {
        this.row = row;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
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

    public String getRow() {
        return row;
    }

    public Integer getRowNum() {
        return rowNum;
    }
}
