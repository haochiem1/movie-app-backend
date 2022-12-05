package com.insert.register.Schedule;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public List<Schedule> getAllSchedules();
    public Schedule getSchedule(int id);
    public int validateSchedule(String movie, int room, Date date, Time start, Time end);
    public void removeSchedule(int id);
}