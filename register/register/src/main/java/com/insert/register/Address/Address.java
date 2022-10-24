package com.insert.register.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraddress")
public class Address {

    @Id
    @Column(name="addressID")
    private int addressID;
    @Column(name="street")
    private String street;
    @Column(name="aptNum")
    private String aptNum;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="zipcode")
    private String zipcode;

    public Address(int addressID, String street, String aptNum, String city, String state, String zipcode) {
        this.addressID = addressID;
        this.street = street;
        this.aptNum = aptNum;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address()
    {
    }

    public void setAddressID (int addressID) {
        this.addressID = addressID;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public void setAptNum (String aptNum) {
        this.aptNum = aptNum;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public void setState (String state) {
        this.state = state;
    }

    public void setZipCode (String zipcode) {
        this.zipcode = zipcode;
    }

    public int getAddressID() {
        return addressID;
    }

    public String getStreet() {
        return street;
    }

    public String getAptNum() {
        return aptNum;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipcode;
    }

    public String getState() {
        return state;
    }
}
