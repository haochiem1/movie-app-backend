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

    @Override
    public Card getCard(int id){
        Card card = getAllCards().stream().filter(a -> a.getId().equals(id)).findFirst().get();

        if(card.getCardNumber1() == null || card.getCardNumber1() == "")
        {
        }
        else
        {
            card.setCardNumber1("************" + card.getCardNumber1().substring(12, 16));
        }
        if(card.getCardNumber2() == null || card.getCardNumber2() == "")
        {
        }
        else
        {
            card.setCardNumber2("************" + card.getCardNumber2().substring(12, 16));
        }
        if(card.getCardNumber3() == null || card.getCardNumber3() == "")
        {
        }
        else
        {
            card.setCardNumber3("************" + card.getCardNumber3().substring(12, 16));
        }
        return card;
    }
}