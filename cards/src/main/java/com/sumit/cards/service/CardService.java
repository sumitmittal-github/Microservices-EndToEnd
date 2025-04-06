package com.sumit.cards.service;

import com.sumit.cards.constants.AppConstant;
import com.sumit.cards.dto.CardDto;
import com.sumit.cards.entity.Card;
import com.sumit.cards.exception.CardAlreadyExistsException;
import com.sumit.cards.exception.ResourceNotFoundException;
import com.sumit.cards.mapper.CardMapper;
import com.sumit.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }
    
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(AppConstant.CREDIT_CARD);
        newCard.setTotalLimit(AppConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(AppConstant.NEW_CARD_LIMIT);
        return newCard;
    }
    
    public CardDto fetchCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardDto(cards, new CardDto());
    }
    
    public boolean updateCard(CardDto CardDto) {
        Card cards = cardRepository.findByCardNumber(CardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", CardDto.getCardNumber()));
        CardMapper.mapToCard(CardDto, cards);
        cardRepository.save(cards);
        return  true;
    }
    
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }


}
