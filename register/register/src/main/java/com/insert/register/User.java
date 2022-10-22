package com.insert.register;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    @Column(name="userName")
    private String userName;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="userEmail")
    private String userEmail;
    @Column(name="userPassword")
    private String userPassword;

    public User(String username, String phoneNumber, String email, String password) {
        //this.userID = id;
        this.userName = username;
        this.phoneNumber = phoneNumber;
        this.userEmail = email;
        this.userPassword = password;
    }

    public User()
    {
    }

    public void setId(Integer id) {
        this.userID = id;
    }

    public Integer getId() {
        return userID;
    }

    public void setUsername (String username) {
        this.userName = username;
    }

    public void phoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail (String email) {
        this.userEmail = email;
    }

    public void setPassword (String password) {
        this.userPassword = password;
    }

    public String getUsername() {
        return userName;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return userPassword;
    }

    public String getEmail() {
        return userEmail;
    }
}
