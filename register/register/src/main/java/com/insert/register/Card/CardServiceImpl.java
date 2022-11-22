package com.insert.register.Card;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public Card getCard(int id){
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();
  
        // if(card.getCardNumber1() == null || card.getCardNumber1() == "")
        // {
        // }
        // else
        // {
        //     card.setCardNumber1("************" + card.getCardNumber1().substring(12, 16));
        // }
        // if(card.getCardNumber2() == null || card.getCardNumber2() == "")
        // {
        // }
        // else
        // {
        //     card.setCardNumber2("************" + card.getCardNumber2().substring(12, 16));
        // }
        // if(card.getCardNumber3() == null || card.getCardNumber3() == "")
        // {
        // }
        // else
        // {
        //     card.setCardNumber3("************" + card.getCardNumber3().substring(12, 16));
        // }
        return card;
    }

    public Card updatePayment1(int id, String cardName1, String cardNumber1, String expMonth1, String expYear1, String billZip1) {
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName1(cardName1);
        if(!(cardNumber1.contains("*"))) {
            card.setCardNumber1(cardNumber1);
        }
        card.setExpMonth1(expMonth1);
        card.setExpYear1(expYear1);
        card.setBillZip1(billZip1);

        return cardRepository.save(card);
    }

    public Card updatePayment2(int id, String cardName2, String cardNumber2, String expMonth2, String expYear2, String billZip2) {        
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName2(cardName2);
        if(!(cardNumber2.contains("*"))) {
            card.setCardNumber2(cardNumber2);
        }
        card.setExpMonth2(expMonth2);
        card.setExpYear2(expYear2);
        card.setBillZip2(billZip2);

        return cardRepository.save(card);
    }

    public Card updatePayment3(int id, String cardName3, String cardNumber3, String expMonth3, String expYear3, String billZip3) {
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName3(cardName3);
        if(!(cardNumber3.contains("*"))) {
            card.setCardNumber3(cardNumber3);
        }
        card.setExpMonth3(expMonth3);
        card.setExpYear3(expYear3);
        card.setBillZip3(billZip3);

        return cardRepository.save(card);
    }
 
    public Card deletePayment1(int id) {
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName1(null);
        card.setCardNumber1(null);
        card.setExpMonth1(null);
        card.setExpYear1(null);
        card.setBillZip1(null);

        return cardRepository.save(card);
    }

    public Card deletePayment2(int id) {
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName2(null);
        card.setCardNumber2(null);
        card.setExpMonth2(null);
        card.setExpYear2(null);
        card.setBillZip2(null);

        return cardRepository.save(card);
    }

    public Card deletePayment3(int id) {
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        card.setCardName3(null);
        card.setCardNumber3(null);
        card.setExpMonth3(null);
        card.setExpYear3(null);
        card.setBillZip3(null);

        return cardRepository.save(card);
    }
}