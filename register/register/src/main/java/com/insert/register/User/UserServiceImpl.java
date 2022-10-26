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


}
