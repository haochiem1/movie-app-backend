package com.insert.register;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
}