package com.insert.register.Card;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CardService {
    public Card saveCard(Card card);
    public List<Card> getAllCards();
    public Card getCard(int id);
    public Card updatePayment1(int id, String cardName1, String cardNumber1, String expMonth1, String expYear1, String billZip1);
    public Card updatePayment2(int id, String cardName2, String cardNumber2, String expMonth2, String expYear2, String billZip2);
    public Card updatePayment3(int id, String cardName3, String cardNumber3, String expMonth3, String expYear3, String billZip3);
    public Card deletePayment1(int id);
    public Card deletePayment2(int id);
    public Card deletePayment3(int id);
}