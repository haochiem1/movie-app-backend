package com.insert.register.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieID;

    @Column(name="title")
    private String title;
    @Column(name="categoryID")
    private Integer categoryID;
    @Column(name="cast")
    private String cast;
    @Column(name="direct")
    private String director;
    @Column(name="producer")
    private String producer;
    @Column(name="synopsis")
    private String synopsis;
    @Column(name="duration")
    private Integer duration;
    @Column(name="rating")
    private String rating;
    @Column(name="reviewsRating")
    private Float reviewsRating;
    @Column(name="releaseDate")
    private String releaseDate;

    public Movie(String title, Integer categoryID, String cast, String director, String producer, String synopsis, Integer duration, String rating, Float reviewsRating, String releaseDate) {
        this.title = title;
        this.categoryID = categoryID;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.synopsis = synopsis;
        this.duration = duration;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewsRating = reviewsRating;
    }

    public Movie() {}

    //Setters
    public void setId(Integer id) {
        this.movieID = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDurating(Integer duration) {
        this.duration = duration;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReviewsRating(Float reviewsRating) {
        this.reviewsRating = reviewsRating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    //Getters
    public Integer getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public Float getReviewsRating() {
        return reviewsRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
