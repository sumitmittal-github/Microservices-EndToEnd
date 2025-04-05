package com.sumit.accounts.service;

import com.sumit.accounts.constant.AppConstant;
import com.sumit.accounts.dto.CustomerDto;
import com.sumit.accounts.entity.Account;
import com.sumit.accounts.entity.Customer;
import com.sumit.accounts.exception.CustomerAlreadyExistsException;
import com.sumit.accounts.mapper.CustomerMapper;
import com.sumit.accounts.repository.AccountRepository;
import com.sumit.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void createAccount(CustomerDto customerDto) {
        // check if any customer exists with same mobile no
        Optional<Customer> dbCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(dbCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already exists with mobile no "+customerDto.getMobileNumber());

        // create customer object
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("SUMIT MITTAL");
        Customer savedCustomer = customerRepository.save(customer);

        // create account object
        Long accountNum = 1000000000L + new Random().nextInt(900000000);
        Account account = new Account();
        account.setCustomerId(savedCustomer.getCustomerId());
        account.setAccountNumber(accountNum);
        account.setAccountType(AppConstant.SAVINGS);
        account.setBranchAddress(AppConstant.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("SUMIT MITTAL");
        //account.setCustomer(customer);            TODO
        accountRepository.save(account);
    }


}