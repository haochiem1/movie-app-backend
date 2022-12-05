package com.insert.register.Movie;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    public List<Movie> getAllMovies();
    public List<Movie> getSearchedMovie(String query);
    public void removeMovie(int id);
    public Movie getMovie(int id);
}