package com.tuliomeran.accounts.service.impl;

import com.tuliomeran.accounts.dto.*;
import com.tuliomeran.accounts.entity.Accounts;
import com.tuliomeran.accounts.entity.Customer;
import com.tuliomeran.accounts.exception.ResourceNoFoundException;
import com.tuliomeran.accounts.mapper.AccountsMapper;
import com.tuliomeran.accounts.mapper.CustomerMapper;
import com.tuliomeran.accounts.repository.AccountsRepository;
import com.tuliomeran.accounts.repository.CustomerRepository;
import com.tuliomeran.accounts.service.ICustomersService;
import com.tuliomeran.accounts.service.client.CardsFeignClient;
import com.tuliomeran.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /***
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNoFoundException("Customer", "mobileNumber", mobileNumber)
                );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNoFoundException("Accounts", "customerId", customer.getCustomerId().toString())
                );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        if (cardsDtoResponseEntity != null ){
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        if(loansDtoResponseEntity != null){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
