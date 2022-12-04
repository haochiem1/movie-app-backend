package com.insert.register.Seat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;
    
    @Override
    public List<Seat> getSeatsFromShowtime(Integer showtimeID) {
        return seatRepository.findAll().stream().filter(a -> a.getShowtime().equals(showtimeID)).collect(Collectors.toList());
    }
}
