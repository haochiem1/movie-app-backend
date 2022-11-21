package com.insert.register.Schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

    @Query(value = "SELECT * from showtime where showtimedate >=:currentDate", nativeQuery = true)
    List<Schedule> findByAfter(@Param("currentDate") LocalDate currentDate); // today's date
}
