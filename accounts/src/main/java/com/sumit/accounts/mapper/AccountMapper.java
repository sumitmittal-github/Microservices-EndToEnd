package com.sumit.accounts.mapper;

import com.sumit.accounts.dto.AccountDto;
import com.sumit.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account accounts) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountsDto) {
        Account account = new Account();
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());
        return account;
    }

}