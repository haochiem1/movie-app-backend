package com.insert.register;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import com.insert.register.Address.AddressRepository;
import com.insert.register.Card.CardRepository;
import com.insert.register.User.*;
import com.insert.register.Card.*;
import com.insert.register.Address.*;
import com.insert.register.Security.*;


import org.springframework.beans.factory.annotation.Autowired;
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
   public ResponseEntity<String> addUser(@RequestBody Map<String, String> body) throws UnsupportedEncodingException, MessagingException
   {
      String firstName = body.get("firstName");
      String lastName = body.get("lastName");
      String userPhonenumber = body.get("phoneNumber");
      String userEmail = body.get("userEmail");
      String userPassword = body.get("userPassword");
      String promotions = body.get("promotions");
      if (userPhonenumber.length() != 10) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter a valid phone number");
      }
      String sql = "SELECT userID FROM userinfo WHERE userEmail = '" + userEmail + "'";
      List<String> ids = jdbcTemplate.queryForList(sql, String.class);
      if (ids.size() == 1) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An account with that email already exists");
      }
      String verificationCode = generateVerificationCode();
      String key = Security.generateSecretKey();
      String encryptedPass = Security.encrypt(userPassword, key);
      String encryptedCode = Security.encrypt(verificationCode, key);
      currUser = new User(firstName, lastName, userPhonenumber, userEmail, encryptedPass ,"Active", promotions, encryptedCode, key);
      userService.sendVerificationEmail(currUser);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("New user created");
   }

   @PostMapping("/verification")
   public ResponseEntity<String> userVerification(@RequestBody Map<String, String> body) throws UnsupportedEncodingException, MessagingException {
      String userVerificationCode = body.get("verificationCode");
      String actualCode = currUser.getVerificationCode();
      String key = currUser.getSecretKey();
      String decryptedCode = Security.decrypt(actualCode, key);
      if (decryptedCode.equals(userVerificationCode)) {
         userRepository.save(currUser);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("verified");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong verification code");
      }
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

   @PostMapping("/update/address")
   public Address updateAddress(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String street = body.get("street");
      String aptNum = body.get("aptNum");
      String city = body.get("city");
      String state = body.get("state");
      String zipcode = body.get("zipCode");
      return addressService.updateAddress(id, street, aptNum, city, state, zipcode);
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

   public String generateVerificationCode() {
      int leftLimit = 48; // numeral '0'
      int rightLimit = 122; // letter 'z'
      int targetStringLength = 5;
      Random random = new Random();
  
      String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

      return generatedString;
   }
   
}

