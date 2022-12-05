package com.insert.register.User;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User getUser(int id);
    public void sendVerificationEmail(User user) throws UnsupportedEncodingException, MessagingException;
    public User updateName(int id, String firstName, String lastName);
    public User updatePhone(int id, String phoneNumber);
    public User updateRegPromotion(int id, Integer registeredPromotion);
    public boolean checkPassword(int id, String password);
    public User updatePassword(int id, String password);
    public User checkLogin(String email, String password);
    public void changePasswordEmail(int id, String email) throws UnsupportedEncodingException, MessagingException;
    public void sendpromoEmail(String first, String last, String email, String promocode, int promopercent) throws UnsupportedEncodingException, MessagingException;
    public void orderConfirmation(User myUser, String movieName, String total, String numAdult, String numChildren, String confirmationCode) throws UnsupportedEncodingException, MessagingException;

}