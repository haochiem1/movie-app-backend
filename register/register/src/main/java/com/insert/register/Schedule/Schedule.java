package com.insert.register.Schedule;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "showtime")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="showtimeID")
    private Integer showtimeID;

    @Column(name="movieShowing")
    private String movieShowing;
    @Column(name="showRoom")
    private int showRoom;
    @Column(name="showtimedate")
    private Date showtimedate;
    @Column(name="showtimestart")
    private Time showtimestart;
    @Column(name="showtimefinish")
    private Time showtimefinish;

    public Schedule(String movieShowing, int showRoom, Date showtimedate, Time showtimestart, Time showtimefinish) {
        this.movieShowing = movieShowing;
        this.showRoom = showRoom;
        this.showtimedate = showtimedate;
        this.showtimestart = showtimestart;
        this.showtimefinish = showtimefinish;
    }

    public Schedule() {}

    public Integer getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(Integer showtimeID) {
        this.showtimeID = showtimeID;
    }

    public Time getShowtimefinish() {
        return showtimefinish;
    }

    public void setShowtimefinish(Time showtimefinish) {
        this.showtimefinish = showtimefinish;
    }

    public Time getShowtimestart() {
        return showtimestart;
    }

    public void setShowtimestart(Time showtimestart) {
        this.showtimestart = showtimestart;
    }

    public Date getShowtimedate() {
        return showtimedate;
    }

    public void setShowtimedate(Date showtimedate) {
        this.showtimedate = showtimedate;
    }

    public int getShowRoom() {
        return showRoom;
    }

    public void setShowRoom(int showRoom) {
        this.showRoom = showRoom;
    }

    public String getMovieShowing() {
        return movieShowing;
    }

    public void setMovieShowing(String movieShowing) {
        this.movieShowing = movieShowing;
    }
    
}