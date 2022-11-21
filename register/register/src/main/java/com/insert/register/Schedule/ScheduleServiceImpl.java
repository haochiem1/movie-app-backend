package com.insert.register.Schedule;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
	private JdbcTemplate jdbcTemplate;

    @Override
    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public int validateSchedule(String movie, int room, Date date, Time start, Time end) {
        String sql1 = "SELECT showtimestart FROM showtime WHERE showRoom = ? AND showtimedate = ?";
        String sql2 = "SELECT showtimefinish FROM showtime WHERE showRoom = ? AND showtimedate = ?";
        List<String> startTimes= jdbcTemplate.queryForList(sql1, String.class, new Object[]{room, date});
        List<String> endTimes= jdbcTemplate.queryForList(sql2, String.class, new Object[]{room, date});
        if (startTimes.size() != 0) {
            String startStr = start.toString();
            String endStr = end.toString();
            for (int i = 0; i < startTimes.size(); i++) {
                String star = startTimes.get(i);
                String en = endTimes.get(i);
                if ((((LocalTime.parse(star).compareTo(LocalTime.parse(startStr))) == 1) || ((LocalTime.parse(star).compareTo(LocalTime.parse(startStr))) == 0)) && (((LocalTime.parse(star).compareTo(LocalTime.parse(endStr))) == -1) || ((LocalTime.parse(star).compareTo(LocalTime.parse(endStr))) == 0))) {
                    return 1; //schedule conflict
                }
                if ((((LocalTime.parse(en).compareTo(LocalTime.parse(startStr))) == 1) || ((LocalTime.parse(en).compareTo(LocalTime.parse(startStr))) == 0)) && (((LocalTime.parse(en).compareTo(LocalTime.parse(endStr))) == -1) || ((LocalTime.parse(en).compareTo(LocalTime.parse(endStr))) == 0))) {
                    return 2; //schedule conflict
                }
            }
        }
        return 0;
    }
}