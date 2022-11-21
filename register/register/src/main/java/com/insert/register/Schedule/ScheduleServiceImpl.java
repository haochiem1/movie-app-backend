package com.insert.register.Schedule;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        Date onePlus = addDays(date, 1);
        System.out.println(onePlus.toString());
        System.out.println(date.toString());
        String sql1 = "SELECT showtimestart FROM showtime WHERE showRoom = ? AND (showtimedate = ? OR showtimedate = ?)";
        String sql2 = "SELECT showtimefinish FROM showtime WHERE showRoom = ? AND (showtimedate = ? OR showtimedate = ?)";
        List<String> startTimes= jdbcTemplate.queryForList(sql1, String.class, new Object[]{room, date, onePlus});
        List<String> endTimes= jdbcTemplate.queryForList(sql2, String.class, new Object[]{room, date, onePlus});
        System.out.println(startTimes.get(0));
        System.out.println(endTimes.get(0));
        return 0;
    }

    public static Date addDays(Date date, int days)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(date);
        LocalDate localDate = LocalDate.parse(strDate);
        localDate = localDate.plusDays(-1);
        String newDate = localDate.toString();
        Date finalDate = java.sql.Date.valueOf(newDate);
        return finalDate;
    }
}