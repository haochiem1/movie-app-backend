package com.insert.register.User;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User getUser(int id);
    private void sendVerificationEmail(User user){}
}