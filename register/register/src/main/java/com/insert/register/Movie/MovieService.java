package com.insert.register.Movie;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    public List<Movie> getAllMovies();
    public List<Movie> getSearchedMovie(String query);
    public void removeMovie(int id);
}