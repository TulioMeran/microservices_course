package com.tuliomeran.accounts.service.client.fallback;

import com.tuliomeran.accounts.dto.CardsDto;
import com.tuliomeran.accounts.service.client.CardsFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient {
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        return null;
    }
}
