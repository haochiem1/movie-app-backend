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
}