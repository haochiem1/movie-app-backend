package com.insert.register.MovieCategoryMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCategoryMappingServiceImpl implements MovieCategoryMappingService{
    @Autowired
    private MovieCategoryMappingRepository movieCategoryMappingRepository;

    @Override
    public List<MovieCategoryMapping> getAllMappings(){
        return movieCategoryMappingRepository.findAll();
    }

    public Integer getMovieIDFromCategoryID(int id) {
        MovieCategoryMapping mapping = getAllMappings().stream().filter(a -> a.getCategoryID().equals(id)).findFirst().get();
        return mapping.getMovieID();
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
