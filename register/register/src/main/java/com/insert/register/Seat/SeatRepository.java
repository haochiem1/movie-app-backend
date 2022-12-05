package com.insert.register.Seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

    @Query(value = "SELECT seat from seat WHERE bookingID = :bookID", nativeQuery = true)
    List<String> findSeats(@Param("bookID") int bookID);
    
}
