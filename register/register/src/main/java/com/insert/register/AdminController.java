package com.insert.register;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import com.insert.register.Movie.MovieRepository;
import com.insert.register.Category.CategoryRepository;
import com.insert.register.MovieCategoryMapping.MovieCategoryMappingRepository;
import com.insert.register.MovieCategoryMapping.*;

import com.insert.register.Movie.*;
import com.insert.register.Category.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.insert.register"})

@Component
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

   private final MovieCategoryMappingRepository movieCategoryMappingRepository;
   private final MovieRepository movieRepository;
   private final CategoryRepository categoryRepository;

   private Movie currMovie;
   private Category currCat;
   private MovieCategoryMapping currMapping;
   
   public AdminController(MovieCategoryMappingRepository movieCategoryMappingRepository, MovieRepository movieRepository, CategoryRepository categoryRepository) {
      this.movieCategoryMappingRepository = movieCategoryMappingRepository;
      this.movieRepository = movieRepository;
      this.categoryRepository = categoryRepository;
   }

   @PostMapping("/add-movie")
   public ResponseEntity<String> addMovie(@RequestBody Map<String, String> body) throws UnsupportedEncodingException, MessagingException
   {
      String title = body.get("title");
      String cast = body.get("cast");
      String director = body.get("director");
      String producer = body.get("producer");
      String synopsis = body.get("synopsis");
      Integer duration = Integer.parseInt(body.get("duration"));
      String rating = body.get("rating");
      Float reviewsRating = Float.parseFloat(body.get("reviewsRating"));
      Date releaseDate = java.sql.Date.valueOf(body.get("releaseDate"));

      currMovie = new Movie(title, cast, director, producer, synopsis, duration, rating, reviewsRating, releaseDate);
      movieRepository.save(currMovie);

      Integer action = Integer.parseInt(body.get("action"));
      Integer adult = Integer.parseInt(body.get("adult"));
      Integer adventure = Integer.parseInt(body.get("adventure"));
      Integer anime = Integer.parseInt(body.get("anime"));
      Integer experimental = Integer.parseInt(body.get("experimental"));
      Integer children = Integer.parseInt(body.get("children"));
      Integer comedy = Integer.parseInt(body.get("comedy"));
      Integer comedyDrama = Integer.parseInt(body.get("comedyDrama"));
      Integer crime = Integer.parseInt(body.get("crime"));
      Integer drama = Integer.parseInt(body.get("drama"));
      Integer epic = Integer.parseInt(body.get("epic"));
      Integer fantasy = Integer.parseInt(body.get("fantasy"));
      Integer historical = Integer.parseInt(body.get("historical"));
      Integer horror = Integer.parseInt(body.get("horror"));
      Integer musical = Integer.parseInt(body.get("musical"));
      Integer mystery = Integer.parseInt(body.get("mystery"));
      Integer romance = Integer.parseInt(body.get("romance"));
      Integer scienceFiction = Integer.parseInt(body.get("scienceFiction"));
      Integer spy = Integer.parseInt(body.get("spy"));
      Integer thriller = Integer.parseInt(body.get("thriller"));
      Integer war = Integer.parseInt(body.get("war"));
      Integer western = Integer.parseInt(body.get("western"));

      currCat = new Category(action, adult, adventure, anime, experimental, children, comedy, comedyDrama, crime, drama, epic, fantasy, historical, horror, musical, mystery, romance, scienceFiction, spy, thriller, war, western);
      categoryRepository.save(currCat);

      /*Integer categoryID = currCat.getCategoryID();
      Integer movieID = currMovie.getMovieID();
      currMapping = new MovieCategoryMapping(movieID, categoryID);
      
      movieCategoryMappingRepository.save(currMapping);
      */
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("3"); // new user created
   }

   
   
}


