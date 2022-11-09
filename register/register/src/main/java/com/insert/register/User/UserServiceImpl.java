package com.insert.register.User;
import com.insert.register.Security.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
        String url = "http://localhost:3000/change-password/" + id;
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
     public User updateRegPromotion(int id, String registeredPromotion)
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
        System.out.println(decrypted);
        return decrypted.equals(password);
     }

     @Transactional
     @Override
     public User checkLogin(String email, String password)
     {
        User user = getAllUsers().stream().filter(a -> a.getEmail().equals(email)).findFirst().get();

        String decrypted = Security.decrypt(user.getPassword(), user.getSecretKey());
        if (decrypted.equals(password)) {
            return user;
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

}
