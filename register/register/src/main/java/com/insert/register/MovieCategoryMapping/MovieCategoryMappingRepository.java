package com.insert.register.MovieCategoryMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCategoryMappingRepository extends JpaRepository<MovieCategoryMapping, Integer>{
    
}
