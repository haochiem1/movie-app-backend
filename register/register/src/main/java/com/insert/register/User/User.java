package com.insert.register.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


enum currentState {
    Active,
    Inactive,
    Suspended;
}

@Entity
@Table(name = "userinfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="userEmail", unique=true)
    private String userEmail;
    @Column(name="userPassword")
    private String userPassword;

    @Column(name="currentState", columnDefinition = "ENUM('Active', 'Inactive', 'Suspended')")
    @Enumerated(EnumType.STRING)
    private currentState state;

    @Column(name="registeredPromotion")
    private Integer promotion;

    @Column(name ="verificationCode")
    private String verificationCode;

    @Column(name = "secretKey")
    private String secretKey;


    public User(String firstname, String lastname, String phoneNumber, String email, String password, String state, Integer promotion, String verificationCode, String secretKey) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNumber = phoneNumber;
        this.userEmail = email;
        this.userPassword = password;
        this.promotion = promotion;
        if (state.equals("Active")) {
            this.state = currentState.Active;
        } else if (state.equals("Inactive")) {
            this.state = currentState.Inactive;
        } else {
            this.state = currentState.Suspended;
        }
        this.verificationCode = verificationCode;
        this.secretKey = secretKey;
    }

    public User()
    {
    }

    public void setId(Integer id) {
        this.userID = id;
    }

    public void setFirstName (String firstname) {
        this.firstName = firstname;
    }

    public void setLastName (String lastname) {
        this.lastName = lastname;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail (String email) {
        this.userEmail = email;
    }

    public void setPassword (String password) {
        this.userPassword = password;
    }

    public void setVerificationCode (String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setPromotion (Integer promotion) {
        this.promotion = promotion;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getSecretKey() {
        return secretKey;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Integer getId() {
        return userID;
    }

    public Integer getPromotion() {
        return promotion;
    }
}
