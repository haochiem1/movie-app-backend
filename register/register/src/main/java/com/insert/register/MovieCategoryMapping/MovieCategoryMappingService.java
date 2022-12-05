package com.insert.register.MovieCategoryMapping;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MovieCategoryMappingService {
    
    public List<MovieCategoryMapping> getAllMappings();
    public Integer getCategoryIDFromMovieID(int id);
    public Integer getMappingIDFromMovieID(int id);
    public void removeMapping(int id);
    public Integer getMovieIDFromCategoryID(int id);
}