package com.insert.register.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

    @Query(value = "select * from movie where releaseDate <=:currentDate", nativeQuery = true)
    List<Movie> findByStartBefore(@Param("currentDate") LocalDate currentDate); //today's date is passed as date and dateCopy

    @Query(value = "select * from movie where releaseDate >:currentDate", nativeQuery = true)
    List<Movie> findByStartAfter(@Param("currentDate") LocalDate currentDate);
}