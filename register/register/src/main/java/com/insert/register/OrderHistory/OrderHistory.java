package com.insert.register.OrderHistory;

import java.sql.Date;
import java.sql.Time;

public class OrderHistory {
    private String movieShowing;
    private int showRoom;
    private Date showtimedate;
    private Time showtimestart;
    private Time showtimefinish;
    private String seats;
    private String total;

    public OrderHistory(String movieShowing, int showRoom, Date showtimedate, Time showtimestart, Time showtimefinish, String seats, String total) {
        this.movieShowing = movieShowing;
        this.showRoom = showRoom;
        this.showtimedate = showtimedate;
        this.showtimestart = showtimestart;
        this.showtimefinish = showtimefinish;
        this.seats = seats;
        this.total = total;
    }

    public OrderHistory() {}

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
