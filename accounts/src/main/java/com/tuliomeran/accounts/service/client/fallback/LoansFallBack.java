package com.tuliomeran.accounts.service.client.fallback;

import com.tuliomeran.accounts.dto.LoansDto;
import com.tuliomeran.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient {
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber) {
        return null;
    }
}
