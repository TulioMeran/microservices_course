package com.tuliomeran.cards.service;

import com.tuliomeran.cards.dto.CardsDto;

public interface ICardService {
    /***
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /***
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return
     */
    CardsDto fetchCard(String mobileNumber);

    /***
     *
     * @param cardsDto
     * @return
     */
    boolean updateCard(CardsDto cardsDto);

    /***
     *
     * @param mobileNumer
     * @return
     */
    boolean deleteCard(String mobileNumer);

}
