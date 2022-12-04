package com.insert.register.Booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
    
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@Table(name = "booking")
public class Booking {
    
        @Id
        @Column(name="bookingID")
        private int bookingID;
        @Column(name="showtimeBooked")
        private int showtimeBooked;
        @Column(name="userBooked")
        private int userBooked;
        @Column(name="creditCardID")
        private int creditCardID;
        @Column(name="promotionID")
        private int promotionID;
        @Column(name="totalCost")
        private double totalCost;
        @Column(name="movieBooked")
        private int movieBooked;
        @Column(name="showroomBooked")
        private int showroomBooked;
    
        public Booking(int bookingID, int showtimeBooked, int userBooked, int creditCardID, int promotionID, double totalCost, int movieBooked, int showroomBooked) {
            this.bookingID = bookingID;
            this.showtimeBooked = showtimeBooked;
            this.userBooked = userBooked;
            this.creditCardID = creditCardID;
            this.promotionID = promotionID;
            this.totalCost = totalCost;
            this.movieBooked = movieBooked;
            this.showroomBooked = showroomBooked;
        }
    
        public Booking()
        {
        }
    
        public void setBookingID (int bookingID) {
            this.bookingID = bookingID;
        }
        public void setShowtimeBooked (int showtimeBooked) {
            this.showtimeBooked = showtimeBooked;
        }
        public void setUserBooked (int userBooked) {
            this.userBooked= userBooked;
        }
        public void setCreditCardID (int creditCardID) {
            this.creditCardID = creditCardID;
        }
        public void setPromotionID (int promotionID) {
            this.promotionID = promotionID;
        }
        public void setTotalCost (double totalCost) {
            this.totalCost = totalCost;
        }
        public void setMovieBooked (int movieBooked) {
            this.movieBooked = movieBooked;
        }
        public void setShowroomBooked (int showroomBooked) {
            this.showroomBooked = showroomBooked;
        }
    
    
        public Integer getBookingID() {
            return bookingID;
        }
        public Integer getShowtimeBooked() {
            return showtimeBooked;
        }
        public Integer getUserBooked() {
            return userBooked;
        }
        public Integer getCreditCardID() {
            return creditCardID;
        }
        public Integer getPromotionID() {
            return promotionID;
        }
        public Double getTotalCost() {
            return totalCost;
        }
        public Integer getMovieBooked() {
            return movieBooked;
        }
        public Integer getShowroomBooked() {
            return showroomBooked;
        }
}
