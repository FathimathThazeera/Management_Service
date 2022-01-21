package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Account;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> create(@RequestBody @Valid Account account) {
        log.info("Received a request to create account : {}", account);
        return new ResponseWrapper(ResultInfoConstants.ACCOUNT_CREATED_SUCCESSFULLY, accountService.create(account));
    }

    @PostMapping("/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> resetPassword(@RequestBody @Valid Account account) {
        log.info("Received a request to reset otp :{}", account);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, accountService.resetPassword(account));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> logIn(@RequestBody @Valid Account account) {
        log.info("Received a request to login :{}", account);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, accountService.logIn(account));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> logOut(@RequestBody @Valid Account account) {
        log.info("Received a request to logout :{}", account);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, accountService.logOut(account));
    }
}
