package com.insert.register;
import java.util.List;
import java.util.Map;

import com.insert.register.Address.AddressRepository;
import com.insert.register.Card.CardRepository;
import com.insert.register.User.*;
import com.insert.register.Card.*;
import com.insert.register.Address.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class APIController {

   @Autowired
	private JdbcTemplate jdbcTemplate;

   @Autowired
   private UserService userService; 

   @Autowired
   private AddressService addressService; 

   @Autowired
   private JavaMailSender mailSender;

   private final UserRepository userRepository;
   private final CardRepository cardRepository;
   private final AddressRepository addressRepository;

   private User currUser;

   public APIController(UserRepository userRepository, CardRepository cardRepository, AddressRepository addressRepository) {
      this.userRepository = userRepository;
      this.cardRepository = cardRepository;
      this.addressRepository = addressRepository;
   }

   @PostMapping("/add")
   public ResponseEntity<String> addUser(@RequestBody Map<String, String> body)
   {
      String firstName = body.get("firstName");
      String lastName = body.get("lastName");
      String userPhonenumber = body.get("phoneNumber");
      String userEmail = body.get("userEmail");
      String userPassword = body.get("userPassword");
      String promotions = body.get("promotions");
      System.out.println(promotions);
      if (userPhonenumber.length() != 10) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter a valid phone number");
      }
      String sql = "SELECT userID FROM userinfo WHERE userEmail = '" + userEmail + "'";
      List<String> ids = jdbcTemplate.queryForList(sql, String.class);
      if (ids.size() == 1) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An account with that email already exists");
      }
      currUser = new User(firstName, lastName, userPhonenumber, userEmail, userPassword,"Inactive", promotions);
      userRepository.save(currUser);
      sendVerificationEmail(currUser);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("New user created");
      
   }

   public void sendVerificationEmail(User user) {
      String subject = "Please verify your registration";
      String senderName = "Fandangotothepolls Team";
      String mailContent = "<P>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>";
      mailContent += "<p>Here is your verification code to verify your registration: "; //+ verificationCode + "</p>"; 
      mailContent += "<p>The Fandangotothepolls Team</p>";
   }

   @PostMapping("/optional")
   public ResponseEntity<String> addOptional(@RequestBody Map<String, String> body)
   {
      String street = body.get("street");
      String aptNum = body.get("aptNum");
      String city = body.get("city");
      String state = body.get("state");
      String zipcode = body.get("zipcode");
      String cardNumber = body.get("cardNumber");
      String cardName = body.get("cardName");
      String expMonth = body.get("expMonth");
      String expYear = body.get("expYear");
      String billZip = body.get("billZip");
      cardRepository.save(new Card(currUser.getId(), cardNumber, cardName, expMonth, expYear, billZip));
      addressRepository.save(new Address(currUser.getId(), street, aptNum, city, state, zipcode));
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("New user created");
   }

   @GetMapping("/getAll")
   public List<User> getAllUsers(){
      return userService.getAllUsers();
   }

   @GetMapping("/getAll/{id}")
   public User getUser(@PathVariable int id){
      return userService.getUser(id);
   }

   @GetMapping("/getAllAddresses/{id}")
   public Address getAddress(@PathVariable int id){
      return addressService.getAddress(id);
   }
   
}

