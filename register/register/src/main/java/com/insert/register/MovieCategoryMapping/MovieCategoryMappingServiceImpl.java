package com.insert.register.MovieCategoryMapping;
import com.insert.register.Security.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieCategoryMappingServiceImpl implements MovieCategoryMappingService{
    @Autowired
    private MovieCategoryMappingRepository movieCategoryMappingRepository;

    @Override
    public List<MovieCategoryMapping> getAllMappings(){
        return movieCategoryMappingRepository.findAll();
    }

    public Integer getCategoryIDFromMovieID(int id) {
        MovieCategoryMapping mapping = getAllMappings().stream().filter(a -> a.getMovieID().equals(id)).findFirst().get();
        return mapping.getCategoryID();
    }

    public Integer getMappingIDFromMovieID(int id) {
        MovieCategoryMapping mapping = getAllMappings().stream().filter(a -> a.getMovieID().equals(id)).findFirst().get();
        return mapping.getID();
    }

    public void removeMapping(int id) {
        MovieCategoryMapping mapping = getAllMappings().stream().filter(a -> a.getID().equals(id)).findFirst().get();
        movieCategoryMappingRepository.delete(mapping);
    }
}
