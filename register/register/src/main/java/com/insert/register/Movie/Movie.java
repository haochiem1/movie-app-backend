package com.insert.register.Movie;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieID;

    @Column(name="title")
    private String title;
    @Column(name="cast")
    private String cast;
    @Column(name="director")
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
    private Date releaseDate;
    @Column(name="posterLink")
    private String posterLink;
    @Column(name="trailerLink")
    private String trailerLink;

    public Movie(String title, String cast, String director, String producer, String synopsis, Integer duration, String rating, Float reviewsRating, Date releaseDate,String posterLink,String trailerLink) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.synopsis = synopsis;
        this.duration = duration;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewsRating = reviewsRating;
        this.posterLink = posterLink;
        this.trailerLink = trailerLink;
    }

    public Movie() {}

    //Setters
    public void setId(Integer id) {
        this.movieID = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public void setReviewsRating(Float reviewsRating) {
        this.reviewsRating = reviewsRating;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    //Getters
    public Integer getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public String getTrailerLink() {
        return trailerLink;
    }
}
