package com.insert.register.Card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name="cardID")
    private int cardID;   
    @Column(name="cardNumber")
    private String cardNumber;
    @Column(name="cardName")
    private String cardName;
    @Column(name="expMonth")
    private String expMonth;
    @Column(name="expYear")
    private String expYear;
    @Column(name="billZip")
    private String billZip;

    public Card(int cardID, String cardNumber, String cardName, String expMonth, String expYear, String billZip) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.billZip = billZip;
    }

    public Card()
    {
    }

    public void setCardId(int cardID) {
        this.cardID = cardID;
    }

    public Integer getId() {
        return cardID;
    }

    public void setCardNumber (String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardName (String cardName) {
        this.cardName = cardName;
    }

    public void setExpMonth (String expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear (String expYear) {
        this.expYear = expYear;
    }

    public void setBillZip (String billZip) {
        this.billZip = billZip;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getcardName() {
        return cardName;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getBillZip() {
        return billZip;
    }

    public String getExpYear() {
        return expYear;
    }
}