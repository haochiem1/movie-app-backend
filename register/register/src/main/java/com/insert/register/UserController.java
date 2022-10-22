package com.insert.register;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.net.URI;
import java.net.URISyntaxException;
import com.insert.register.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

   @Autowired
	private JdbcTemplate jdbcTemplate;

   @Autowired
   private UserService userService; 

   private final UserRepository userRepository;

   public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @PostMapping("/add")
   public User add(@RequestBody Map<String, String> body)
   {
      String userName = body.get("userName");
      String userPhonenumber = body.get("phoneNumber");
      String userEmail = body.get("userEmail");
      String userPassword = body.get("userPassword");
      return userRepository.save(new User(userName, userPhonenumber, userEmail, userPassword));
      //userService.saveUser(user);
      //return "New user is added";
   }

   @GetMapping("/getAll")
   public List<User> getAllUsers(){
      return userService.getAllUsers();
   }

   @PostMapping(consumes = "application/x-www-form-urlencoded")
   @ResponseStatus(HttpStatus.OK)
   public void create(User user) {
      System.out.println(user.getUsername());
      //userRepository.save(user);
   }
   /* 
   public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
      User savedUser = userRepository.save(user);
      return ResponseEntity.created(new URI("/user" + savedUser.getId())).body(savedUser);
   }
   */

}

