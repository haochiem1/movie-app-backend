package com.insert.register.MovieCategoryMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movieCategoryMapping")
public class MovieCategoryMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mappingID;

    @Column(name="movieID")
    private Integer movieID;
    @Column(name="categoryID")
    private Integer categoryID;

    public MovieCategoryMapping(Integer movieID, Integer categoryID) {
        this.movieID = movieID;
        this.categoryID = categoryID;
    }

    public MovieCategoryMapping() {}

    //Setters
    public void setId(Integer id) {
        this.mappingID = id;
    }

    public void setCategoryID(Integer id) {
        this.categoryID = id;
    }

    public void setMovieID(Integer id) {
        this.movieID = id;
    }

    //Getters
    public Integer getID() {
        return mappingID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public Integer getMovieID() {
        return movieID;
    }
}
