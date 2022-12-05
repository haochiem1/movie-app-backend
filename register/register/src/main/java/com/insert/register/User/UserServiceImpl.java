package com.insert.register.User;
import com.insert.register.Security.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id){
        return getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();
    }

    public void sendVerificationEmail(User user) throws UnsupportedEncodingException, MessagingException {

        String verificationCode = user.getVerificationCode();
        String key = user.getSecretKey();
        String decryptedCode = Security.decrypt(verificationCode, key);
        String url = "http://localhost:3000/email-verification";
        String subject = "Please verify your registration";
        String senderName = "Fandangotothepolls Team";
        String mailContent = "<P>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>";
        mailContent += "<p>Here is your verification code to verify your registration: " + decryptedCode + "</p>"; 
        mailContent += "<h3><a href=\"" + url + "\">VERIFY</a></h3>";
        mailContent += "<p>The Fandangotothepolls Team</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Fandangotothepolls@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        helper.setText(mailContent, true);

        mailSender.send(message);
     }

     public void changePasswordEmail(int id, String email) throws UnsupportedEncodingException, MessagingException {
        User user = getUser(id);
        String userEmail = user.getEmail();
        String encryptedEmail = Security.encrypt(userEmail, "ronaldosuiii");
        String urlEncoded = URLEncoder.encode(encryptedEmail, "UTF-8");
        String url = "http://localhost:3000/change-password/" + urlEncoded;
        String subject = "Change Password";
        String senderName = "Fandangotothepolls Team";
        String mailContent = "<P>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>";
        mailContent += "<p>Please follow this link to change your password...</p>"; 
        mailContent += "<h3><a href=\"" + url + "\">CHANGE PASSWORD</a></h3>";
        mailContent += "<p>The Fandangotothepolls Team</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Fandangotothepolls@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        helper.setText(mailContent, true);

        mailSender.send(message);
     }

     public void orderConfirmation(User myUser, String movieName, String total, String numAdult, String numChildren, String seats, String room, String date, String time) throws UnsupportedEncodingException, MessagingException {
        String subject = "Order Confirmation";
        String senderName = "Fandangotothepolls Team";
        String mailContent = "<P>Dear " + myUser.getFirstName() + " " + myUser.getLastName() + ",</p>";
        String adultMSG = "";
        String childMSG = "";
        if (Integer.parseInt(numAdult) > 0) {
            adultMSG = numAdult + "x adult tickets";
        }
        if (Integer.parseInt(numChildren) > 0) {
            if (Integer.parseInt(numAdult) > 0) {
                childMSG = " and " + numChildren + "x child tickets.";
            } else {
                childMSG = numChildren + "x child tickets.";
            }
        }
        int numCom = countCommas(seats);
        String seatMSG = "seat ";
        if (numCom > 0) {
            seatMSG = "seats ";
        }
        mailContent += "<p>Your order for the movie " + movieName + " on " + date + " at " + time + " has been received!</p>"; 
        mailContent += "<p>You ordered " + adultMSG + childMSG + "</p>"; 
        mailContent += "<p>The movie will be in showroom #" + room + " and you have reserved " + seatMSG + msgSep(seats) + "</p>"; 
        mailContent += "<p>Your total came out to $" + total + ", please let us know if you have any questions!</p>";
        mailContent += "<p>The Fandangotothepolls Team</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Fandangotothepolls@gmail.com", senderName);
        helper.setTo(myUser.getEmail());
        helper.setSubject(subject);

        helper.setText(mailContent, true);

        mailSender.send(message);
     }

     public String msgSep(String seats) {
        String endStr = "";
        for (int i = 0; i < seats.length(); i++) {
            if (seats.charAt(i) == ',') {
                endStr += ", ";
            } else {
                endStr += seats.charAt(i);
            }
        }
        return endStr;
     }

     public int countCommas(String seats) {
        int count = 0;
        for (int i = 0; i < seats.length(); i++) {
            if (seats.charAt(i) == ',') {
                count++;
            }
        }
        return count;
     }

     @Transactional
     @Override
     public User updateName(int id, String firstName, String lastName)
     {
         User user = getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();
 
         user.setFirstName(firstName);
         user.setLastName(lastName);
         
         return userRepository.save(user);
     }

     @Transactional
     @Override
     public User updatePhone(int id, String phoneNumber)
     {
         User user = getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();
 
         user.setPhoneNumber(phoneNumber);
         return userRepository.save(user);
     }

     @Transactional
     @Override
     public User updateRegPromotion(int id, Integer registeredPromotion)
     {
         User user = getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();
         System.out.println(user.getPromotion());

         user.setPromotion(registeredPromotion);
         System.out.println(user.getPromotion());
         return userRepository.save(user);
     }

     @Transactional
     @Override
     public boolean checkPassword(int id, String password)
     {
        User user = getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        String decrypted = Security.decrypt(user.getPassword(), user.getSecretKey());
        return decrypted.equals(password);
     }

     @Transactional
     @Override
     public User checkLogin(String email, String password)
     {
        try {
            User user = getAllUsers().stream().filter(a -> a.getEmail().equals(email)).findFirst().get();
            String decrypted = Security.decrypt(user.getPassword(), user.getSecretKey());
            if (decrypted.equals(password)) {
                return user;
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        return null;
     }

     @Transactional
     @Override
     public User updatePassword(int id, String password)
     {
        User user = getAllUsers().stream().filter(a -> a.getId().equals(id)).findFirst().get();
        String encrypted = Security.encrypt(password, user.getSecretKey());
        user.setPassword(encrypted);
        return userRepository.save(user);
     }
    public void sendpromoEmail(String first, String last, String email, String promocode, int promopercent) throws UnsupportedEncodingException, MessagingException {

        String subject = "Fandangotothepolls Promotion";
        String senderName = "Fandangotothepolls Team";
        String mailContent = "<P>Dear " + first + " " + last + ",</p>";
        mailContent += "<p>Here is a promotion for you " + promocode + " which is "+ promopercent + "% off! </p>";
        mailContent += "<p>The Fandangotothepolls Team</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Fandangotothepolls@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        helper.setText(mailContent, true);

        mailSender.send(message);

    }

}
