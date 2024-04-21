package com.tuliomeran.accounts.service;

import com.tuliomeran.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    /***
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
