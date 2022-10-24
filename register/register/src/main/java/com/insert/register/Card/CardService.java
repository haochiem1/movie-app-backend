package com.insert.register.Card;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CardService {
    public Card saveCard(Card card);
    public List<Card> getAllCards();
}