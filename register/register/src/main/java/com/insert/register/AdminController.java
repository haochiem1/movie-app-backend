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

   @Autowired
   private MovieService movieService; 

   @Autowired
   private CategoryService categoryService; 

   @Autowired
   private MovieCategoryMappingService mappingService; 

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
      String posterLink = body.get("posterLink");
      String trailerLink = body.get("trailerLink");



      currMovie = new Movie(title, cast, director, producer, synopsis, duration, rating, reviewsRating, releaseDate, posterLink, trailerLink);
      movieRepository.save(currMovie);

      Integer action = 0;
      if(body.get("action") == "true") action = 1;
      Integer adult = 0;
      if(body.get("adult") == "true") adult = 1;
      Integer adventure = 0;
      if(body.get("adventure") == "true") adventure = 1;
      Integer anime = 0;
      if(body.get("anime") == "true") anime = 1;
      Integer experimental = 0;
      if(body.get("experimental") == "true") experimental = 1;
      Integer children = 0;
      if(body.get("children") == "true") children = 1;
      Integer comedy = 0;
      if(body.get("comedy") == "true") comedy = 1;
      Integer comedyDrama = 0;
      if(body.get("comedyDrama") == "true") comedy = 1;
      Integer crime = 0;
      if(body.get("crime") == "true") crime = 1;
      Integer drama = 0;
      if(body.get("drama") == "true") drama = 1;
      Integer epic = 0;
      if(body.get("epic") == "true") epic = 1;
      Integer fantasy = 0;
      if(body.get("fantasy") == "true") fantasy = 1;
      Integer historical = 0;
      if(body.get("historical") == "true") historical = 1;
      Integer horror = 0;
      if(body.get("horror") == "true") horror = 1;
      Integer musical = 0;
      if(body.get("musical") == "true") musical = 1;
      Integer mystery = 0;
      if(body.get("mystery") == "true") mystery = 1;
      Integer romance = 0;
      if(body.get("romance") == "true") romance = 1;
      Integer scienceFiction = 0;
      if(body.get("scienceFiction") == "true") scienceFiction = 1;
      Integer spy = 0;
      if(body.get("spy") == "true") spy = 1;
      Integer thriller = 0;
      if(body.get("thriller") == "true") thriller = 1;
      Integer war = 0;
      if(body.get("war") == "true") war = 1;
      Integer western = 0;
      if(body.get("wester") == "true") western = 1;
      

      currCat = new Category(action, adult, adventure, anime, experimental, children, comedy, comedyDrama, crime, drama, epic, fantasy, historical, horror, musical, mystery, romance, scienceFiction, spy, thriller, war, western);
      categoryRepository.save(currCat);

      Integer categoryID = currCat.getCategoryID();
      Integer movieID = currMovie.getMovieID();
      currMapping = new MovieCategoryMapping(movieID, categoryID);
      
      movieCategoryMappingRepository.save(currMapping);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("3"); // new user created
   }

   @PostMapping("/remove-movie")
   public ResponseEntity<String> removeMovie(@RequestBody Map<String, String> body) throws UnsupportedEncodingException, MessagingException
   {
      Integer movieID = Integer.parseInt(body.get("movieID"));

      Integer categoryID = mappingService.getCategoryIDFromMovieID(movieID);
      Integer mappingID = mappingService.getMappingIDFromMovieID(movieID);
      
      mappingService.removeMapping(mappingID);
      movieService.removeMovie(movieID);
      categoryService.removeCategory(categoryID);
      

      return ResponseEntity.status(HttpStatus.ACCEPTED).body("3"); 
   }
   
}


