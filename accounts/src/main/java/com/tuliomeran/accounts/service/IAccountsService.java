package com.tuliomeran.accounts.service;
import com.tuliomeran.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccounts(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
    boolean updateCommunicationStatus(Long accountNumber);
}
