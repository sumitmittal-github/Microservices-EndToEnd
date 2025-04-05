package com.sumit.accounts.controller;

import com.sumit.accounts.constant.AppConstant;
import com.sumit.accounts.dto.CustomerDto;
import com.sumit.accounts.dto.ResponseDto;
import com.sumit.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);
        ResponseDto responseDto = new ResponseDto(AppConstant.STATUS_201, AppConstant.MESSAGE_201);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }



}