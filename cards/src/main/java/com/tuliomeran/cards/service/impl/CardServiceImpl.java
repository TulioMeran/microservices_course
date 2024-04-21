package com.tuliomeran.cards.service.impl;

import com.tuliomeran.cards.constants.CardsConstants;
import com.tuliomeran.cards.dto.CardsDto;
import com.tuliomeran.cards.entity.Cards;
import com.tuliomeran.cards.exception.CardAlreadyExistsException;
import com.tuliomeran.cards.exception.ResourceNoFoundException;
import com.tuliomeran.cards.mapper.CardsMapper;
import com.tuliomeran.cards.repository.CardsRepository;
import com.tuliomeran.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardsRepository cardsRepository;

    /***
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /***
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNoFoundException("Card", "mobileNumber", mobileNumber)
                );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    /***
     *
     * @param cardsDto
     * @return
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(
                        () -> new ResourceNoFoundException("Card", "CardNumber", cardsDto.getCardNumber())
                );
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    /***
     *
     * @param mobileNumer
     * @return
     */
    @Override
    public boolean deleteCard(String mobileNumer) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumer)
                .orElseThrow(
                        () -> new ResourceNoFoundException("Card","mobileNumber", mobileNumer)
                );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
