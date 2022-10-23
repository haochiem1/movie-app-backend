package com.insert.register;

import java.net.URI;
import java.net.URISyntaxException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

   @Autowired
	private JdbcTemplate jdbcTemplate;

   private final UserRepository userRepository;

   public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
   }


   @PostMapping("/add")
   public String create(@RequestBody User user) {
      return "New User Created";
      //userRepository.save(user);
   }
   /* 
   public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
      User savedUser = userRepository.save(user);
      return ResponseEntity.created(new URI("/user" + savedUser.getId())).body(savedUser);
   }
   */
            
}

