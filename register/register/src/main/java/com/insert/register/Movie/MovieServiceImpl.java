package com.insert.register.Movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
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
