package com.insert.register;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.mail.MessagingException;

import com.insert.register.Movie.MovieRepository;
import com.insert.register.Category.CategoryRepository;
import com.insert.register.MovieCategoryMapping.MovieCategoryMappingRepository;
import com.insert.register.Schedule.Schedule;
import com.insert.register.Schedule.ScheduleRepository;
import com.insert.register.Schedule.ScheduleService;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private JdbcTemplate jdbcTemplate;
   
   @Autowired
   private MovieService movieService;

   @Autowired
   private ScheduleService scheduleService;

   @Autowired
   private CategoryService categoryService; 

   @Autowired
   private MovieCategoryMappingService mappingService; 

   private final MovieCategoryMappingRepository movieCategoryMappingRepository;
   private final MovieRepository movieRepository;
   private final CategoryRepository categoryRepository;
   private final ScheduleRepository scheduleRepository;

   private Movie currMovie;
   private Category currCat;
   private MovieCategoryMapping currMapping;
   
   public AdminController(MovieCategoryMappingRepository movieCategoryMappingRepository, MovieRepository movieRepository, CategoryRepository categoryRepository, ScheduleRepository scheduleRepository) {
      this.movieCategoryMappingRepository = movieCategoryMappingRepository;
      this.movieRepository = movieRepository;
      this.categoryRepository = categoryRepository;
      this.scheduleRepository = scheduleRepository;
   }

   @GetMapping("/getMovies")
   public @ResponseBody List<String> getMovies() {
      List<Movie> movies = movieService.getAllMovies();
      List<String> movieNames = new ArrayList<>();
      for (int i = 0; i < movies.size(); i++) {
         movieNames.add(movies.get(i).getTitle());
      }
      return movieNames;
   }

   @PostMapping("/scheduleMovie")
   public ResponseEntity<String> scheduleMovie(@RequestBody Map<String, String> body) throws ParseException{
      String movie = body.get("movie");
      int room = Integer.parseInt(body.get("room"));
      Date date = java.sql.Date.valueOf(body.get("date"));
      int hour = Integer.parseInt(body.get("hour"));
      int min = Integer.parseInt(body.get("min"));
      String sql = "SELECT duration FROM movie WHERE title = ?";
      List<String> result= jdbcTemplate.queryForList(sql, String.class, new Object[]{movie});
      String sql2 = "SELECT releaseDate FROM movie WHERE title = ?";
      List<String> result2 = jdbcTemplate.queryForList(sql2, String.class, new Object[]{movie});
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String myDate = body.get("date");
      String myDate2 = result2.get(0);
      java.util.Date d1 = sdf.parse(myDate);
      java.util.Date d2 = sdf.parse(myDate2);
      if (d1.compareTo(d2) < 0) {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("4"); // schedule before release date
      }
      int duration = Integer.parseInt(result.get(0));
      int durHour = duration / 60;
      int durMin = duration % 60;
      int endMin = durMin + min;
      if (endMin > 59) {
         endMin = endMin - 60;
         hour++;
      }
      int endHour = durHour + hour;
      if (endHour > 24) {
         endHour = endHour - 24;
      }
      Time start = new Time(hour, min, 0);
      Time end = new Time(endHour, endMin, 0);
      int valid = scheduleService.validateSchedule(movie, room, date, start, end);
      if (valid == 1) {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("1"); // invalid start time
      } else if (valid == 2) {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("2"); // invalid end time
      }
      Schedule mySchedule = new Schedule(movie, room, date, start, end);
      scheduleRepository.save(mySchedule);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("3"); // new scedule created
   }

   @GetMapping("/getAllSchedules")
   public List<Schedule> getAllCurrentSchedules() {
      LocalDate date = LocalDate.now();
      List<Schedule> currentSchedules = scheduleRepository.findByAfter(date);
      return currentSchedules;
   }

   @PostMapping("/remove-schedule")
   public ResponseEntity<String> removeSchedule(@RequestBody Map<String, String> body) {
      Integer showtimeID = Integer.parseInt(body.get("scheduleID"));
      scheduleService.removeSchedule(showtimeID);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("1"); // schedule successfully removed
   }

   @GetMapping("/getRooms")
   public @ResponseBody List<String> getRooms() {
      String sql = "SELECT * FROM showroom";
      List<String> rooms = jdbcTemplate.queryForList(sql, String.class);
      return rooms;
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
      if(body.get("comedyDrama") == "true") comedyDrama = 1;
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
      if(body.get("western") == "true") western = 1;
      
      if (currMovie.getRating().equals("Not Selected")) {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("2");
      } else if (action == 0 && adult == 0 && adventure == 0 && anime == 0 && experimental == 0 && children == 0 && comedy == 0 && comedyDrama == 0 && crime == 0 && drama == 0 && epic == 0 && fantasy == 0 && historical == 0 && horror == 0 && musical == 0 && mystery == 0 && romance == 0 && scienceFiction == 0 && spy == 0 && thriller == 0 && war == 0 && western == 0) {
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("2");
      }
      currCat = new Category(action, adult, adventure, anime, experimental, children, comedy, comedyDrama, crime, drama, epic, fantasy, historical, horror, musical, mystery, romance, scienceFiction, spy, thriller, war, western);
      categoryRepository.save(currCat);
      movieRepository.save(currMovie);

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
   
   @GetMapping("/getMovieCategories/{id}")
   public Category getMovieCategories(@PathVariable String id) 
   {
      Integer movieID = Integer.parseInt(id);

      Integer categoryID = mappingService.getCategoryIDFromMovieID(movieID);
      
      
      

      return categoryService.getCategory(categoryID); 
   }
}


