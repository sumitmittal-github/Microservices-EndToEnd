package com.sumit.accounts.mapper;

import com.sumit.accounts.dto.AccountDto;
import com.sumit.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDTO(Account accounts, AccountDto accountDto) {
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountsDto, Account account) {
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());
        return account;
    }

}