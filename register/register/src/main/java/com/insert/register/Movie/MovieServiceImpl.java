package com.insert.register.Movie;

import com.insert.register.Security.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDate;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }
    
    public List<Movie> getSearchedMovie(String query)
    {
        List<Movie> wholeRepository = movieRepository.findAll();
        if(query.equals("="))
        {
            return wholeRepository;
        }
        
        List<Movie> searchedRepository = movieRepository.findAll();
        searchedRepository.clear();

        for (Movie movie: wholeRepository) {

            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                searchedRepository.add(movie);
            }
        }

        return searchedRepository;
    }

    public void removeMovie(int id) {
        Movie movie = getAllMovies().stream().filter(a -> a.getMovieID().equals(id)).findFirst().get();
        movieRepository.delete(movie);
    }

    public Movie getMovie(int id)
    {
        return getAllMovies().stream().filter(a -> a.getMovieID().equals(id)).findFirst().get();
    }
}
