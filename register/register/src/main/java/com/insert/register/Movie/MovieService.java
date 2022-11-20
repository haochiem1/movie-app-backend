package com.insert.register.Movie;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    public List<Movie> getAllMovies();
    public void removeMovie(int id);
}