package com.insert.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private Long phoneNumber;
    private String email;
    private String password;

    public User(Long id, String username, Long phoneNumber, String email, String password) {
        System.out.println(password);
        this.id = id;
        this.userName = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername (String username) {
        this.userName = username;
    }

    public void phoneNumber (Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public Long getPhonenumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
