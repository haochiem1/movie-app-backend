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
    @Column(name="cardNumber1")
    private String cardNumber1;
    @Column(name="cardName1")
    private String cardName1;
    @Column(name="expMonth1")
    private String expMonth1;
    @Column(name="expYear1")
    private String expYear1;
    @Column(name="billZip1")
    private String billZip1;

    @Column(name="cardNumber2")
    private String cardNumber2;
    @Column(name="cardName2")
    private String cardName2;
    @Column(name="expMonth2")
    private String expMonth2;
    @Column(name="expYear2")
    private String expYear2;
    @Column(name="billZip2")
    private String billZip2;

    @Column(name="cardNumber3")
    private String cardNumber3;
    @Column(name="cardName3")
    private String cardName3;
    @Column(name="expMonth3")
    private String expMonth3;
    @Column(name="expYear3")
    private String expYear3;
    @Column(name="billZip3")
    private String billZip3;


    public Card(int cardID, String cardNumber1, String cardName1, String expMonth1, String expYear1, String billZip1, String cardNumber2, String cardName2, String expMonth2, String expYear2, String billZip2, String cardNumber3, String cardName3, String expMonth3, String expYear3, String billZip3) {
        this.cardID = cardID;
        this.cardNumber1 = cardNumber1;
        this.cardName1 = cardName1;
        this.expMonth1 = expMonth1;
        this.expYear1 = expYear1;
        this.billZip1 = billZip1;

        this.cardNumber2 = cardNumber2;
        this.cardName2 = cardName2;
        this.expMonth2 = expMonth2;
        this.expYear2 = expYear2;
        this.billZip2 = billZip2;

        this.cardNumber3 = cardNumber3;
        this.cardName3 = cardName3;
        this.expMonth3 = expMonth3;
        this.expYear3 = expYear3;
        this.billZip3 = billZip3;
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

    public void setCardNumber1 (String cardNumber1) {
        this.cardNumber1 = cardNumber1;
    }

    public void setCardName1 (String cardName1) {
        this.cardName1 = cardName1;
    }

    public void setExpMonth1 (String expMonth1) {
        this.expMonth1 = expMonth1;
    }

    public void setExpYear1 (String expYear1) {
        this.expYear1 = expYear1;
    }

    public void setBillZip1 (String billZip1) {
        this.billZip1 = billZip1;
    }

    public String getCardNumber1() {
        return cardNumber1;
    }

    public String getcardName1() {
        return cardName1;
    }

    public String getExpMonth1() {
        return expMonth1;
    }

    public String getBillZip1() {
        return billZip1;
    }

    public String getExpYear1() {
        return expYear1;
    }
    

    ////////////////////////////////////////////////////////////////////////////////
    public void setCardNumber2 (String cardNumber2) {
        this.cardNumber2 = cardNumber2;
    }

    public void setCardName2 (String cardName2) {
        this.cardName2 = cardName2;
    }

    public void setExpMonth2 (String expMonth2) {
        this.expMonth2 = expMonth2;
    }

    public void setExpYear2 (String expYear2) {
        this.expYear2 = expYear2;
    }

    public void setBillZip2 (String billZip2) {
        this.billZip2 = billZip2;
    }

    public String getCardNumber2() {
        return cardNumber2;
    }

    public String getcardName2() {
        return cardName2;
    }

    public String getExpMonth2() {
        return expMonth2;
    }

    public String getBillZip2() {
        return billZip2;
    }

    public String getExpYear2() {
        return expYear2;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public void setCardNumber3 (String cardNumber3) {
        this.cardNumber3 = cardNumber3;
    }

    public void setCardName3 (String cardName3) {
        this.cardName3 = cardName3;
    }

    public void setExpMonth3 (String expMonth3) {
        this.expMonth3 = expMonth3;
    }

    public void setExpYear3 (String expYear3) {
        this.expYear3 = expYear3;
    }

    public void setBillZip3 (String billZip3) {
        this.billZip3 = billZip3;
    }

    public String getCardNumber3() {
        return cardNumber3;
    }

    public String getcardName3() {
        return cardName3;
    }

    public String getExpMonth3() {
        return expMonth3;
    }

    public String getBillZip3() {
        return billZip3;
    }

    public String getExpYear3() {
        return expYear3;
    }
}