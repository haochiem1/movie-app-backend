package com.insert.register;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;
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
   private CardService cardService;

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
      Integer promotions = 1;
      if (body.get("promotions").equals("false")) {
         promotions = 0;
      }
      if (userPhonenumber.length() != 10) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("1"); // not a valid phone number
      }
      String sql = "SELECT userID FROM userinfo WHERE userEmail = ?";
      List<String> ids = jdbcTemplate.queryForList(sql, String.class, new Object[]{userEmail});
      if (ids.size() == 1) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("2"); // an account with that email already exists
      }
      String verificationCode = generateVerificationCode();
      String key = Security.generateSecretKey();
      String encryptedPass = Security.encrypt(userPassword, key);
      String encryptedCode = Security.encrypt(verificationCode, key);
      Integer isAdmin = 0;
      currUser = new User(firstName, lastName, userPhonenumber, userEmail, encryptedPass ,"Active", promotions, encryptedCode, key, isAdmin);
      userService.sendVerificationEmail(currUser);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("3"); // new user created
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

   @PostMapping("/emailPassword")
   public ResponseEntity<String> emailPassword(@RequestBody Map<String, String> body) throws UnsupportedEncodingException, MessagingException {
      String inputEmail = body.get("email");
      String sql = "SELECT userID FROM userinfo WHERE userEmail = ?";
      List<String> account = jdbcTemplate.queryForList(sql, String.class, new Object[]{inputEmail});
      if (account.size() == 1) {
         int id = Integer.parseInt(account.get(0));
         userService.changePasswordEmail(id, inputEmail);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("1"); // email to change password was sent
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("2"); // account with that email does not exist
      }
   }
   
   @PostMapping("/changePassword")
   public ResponseEntity<String> changePassword(@RequestBody Map<String, String> body) throws UnsupportedEncodingException {
      String userEmail = body.get("email");
      String newPassword = body.get("password1");
      String urlDecoded = URLDecoder.decode(userEmail, "UTF-8");
      String decryptedEmail = Security.decrypt(urlDecoded, "ronaldosuiii");
      String sql = "SELECT userID FROM userinfo WHERE userEmail = ?";
      List<String> ids = jdbcTemplate.queryForList(sql, String.class, new Object[]{decryptedEmail});
      String userID = ids.get(0);
      userService.updatePassword(Integer.parseInt(userID), newPassword);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("1"); // password was changed
   }


   @PostMapping("/optional")
   public ResponseEntity<String> addOptional(@RequestBody Map<String, String> body)
   {
      String street = body.get("street");
      String aptNum = body.get("aptNum");
      String city = body.get("city");
      String state = body.get("state");
      String zipcode = body.get("zipcode");
      String cardNumber1 = body.get("cardNumber");
      String cardName1 = body.get("cardName");
      String expMonth1 = body.get("expMonth");
      String expYear1 = body.get("expYear");
      String billZip1 = body.get("billZip");
      cardRepository.save(new Card(currUser.getId(), cardNumber1, cardName1, expMonth1, expYear1, billZip1, null, null, null, null, null, null, null, null, null, null));
      addressRepository.save(new Address(currUser.getId(), street, aptNum, city, state, zipcode));
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("New user created");
   }

   @Transactional
   @PostMapping("/update/address")
   public void updateAddress(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String street = body.get("street");
      String aptNum = body.get("aptNum");
      String city = body.get("city");
      String state = body.get("state");
      String zipcode = body.get("zipCode");
 
      addressService.updateAddress(id, street, aptNum, city, state, zipcode);
   }

   @Transactional
   @PostMapping("/update/name")
   public void updateName(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String firstName = body.get("firstName");
      String lastName = body.get("lastName");

      userService.updateName(id, firstName, lastName);
   }

   @Transactional
   @PostMapping("/update/phone-number")
   public void updatePhone(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String phoneNumber = body.get("phoneNumber");
      System.out.println(phoneNumber);

      userService.updatePhone(id, phoneNumber);
   }

   @Transactional
   @PostMapping("/update/password")
   public void updatePassword(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String password = body.get("password");

      userService.updatePassword(id, password);
   }

   @Transactional
   @PostMapping("/update/registered-promotion")
   public void updateRegPromotion(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      int registeredPromotion = Integer.parseInt(body.get("registeredPromotion"));
      System.out.println(registeredPromotion);

      userService.updateRegPromotion(id, registeredPromotion);
   }

   @Transactional
   @PostMapping("/check/password")
   public ResponseEntity<String> checkPassword(@RequestBody Map<String, String> body)
   {
      int id = Integer.parseInt(body.get("id"));
      String password = body.get("password");
      String newPassword = body.get("newPassword");
      boolean isCorrect = userService.checkPassword(id, password);
      if (isCorrect) {
         userService.updatePassword(id, newPassword);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("1"); // passwords match and was updated
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("2"); //password does not match
      }
   }

   @Transactional
   @PostMapping("/check/login")
   public List<Integer> checkLogin(@RequestBody Map<String, String> body)
   {
      String email = body.get("userEmail");
      String password = body.get("userPassword");

      User user = userService.checkLogin(email, password);
      List<Integer> userInfo = new ArrayList<>();
      if (user == null) {
         userInfo.add(-1);
      } else {
         userInfo.add(user.getId());
         userInfo.add(user.getPromotion());
      }
      return userInfo;
   }

   @GetMapping("/getAll")
   public List<User> getAllUsers(){
      return userService.getAllUsers();
   }

   @GetMapping("/getAll/{id}")
   public User getUser(@PathVariable int id){
      return userService.getUser(id);
   }

   @GetMapping("/getPayment/{id}")
   public Card getCard(@PathVariable int id){
      Card card = cardService.getCard(id);
      return card;
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

