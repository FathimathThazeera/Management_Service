package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Account;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.AccountTable;
import com.example.demo.repository.table.OtpRepository;
import com.example.demo.repository.table.OtpTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    private final OtpRepository otpRepository;


    public Long create(Account account) {
        if (account.getPhone() == null || account.getPassword() == 0) {
            log.warn("Enter phone number and password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_PHONE_NUMBER_AND_PASSWORD);
        }
        if (accountRepository.existsById(account.getPhone())) {
            log.warn("Duplicate message is trying to be inserted/ ACCOUNT_ALREADY_EXISTING");
            throw new DuplicateKeyException(ResultInfoConstants.ACCOUNT_ALREADY_EXISTING);
        }

        int number = String.valueOf(account.getPhone()).length();
        if (number > 10 || number < 10) {
            log.warn("Enter 10 digit phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_10_DIGIT_PHONE_NUMBER);
        }
        int password = String.valueOf(account.getPassword()).length();
        if (password > 4 || password < 4) {
            log.warn("Enter 4 digit password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_4_DIGIT_Password);
        }
        Long num = account.getPhone();
        AccountTable accountTable = accountRepository.getUser(num);
        if (accountTable != null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_ALREADY_EXISTING);
        }
        OtpTable otpTable = otpRepository.getUser(num);
        if (otpTable == null) {
            log.warn("Generate otp");
            throw new DuplicateKeyException(ResultInfoConstants.GENERATE_OTP);
        }
        if (account.getOtp() != otpTable.getOtp()) {
            log.warn("Put valid otp");
            throw new DuplicateKeyException(ResultInfoConstants.PUT_VALID_OTP);
        }
        otpRepository.deleteById(num);
        return accountRepository.save(account.toAccountTable()).getPhone();
    }

    public Long resetPassword(Account account) {

        if (account.getPhone() == null || account.getPassword() == 0) {
            log.warn("Enter phone number and password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_PHONE_NUMBER_AND_PASSWORD);
        }

        int number = String.valueOf(account.getPhone()).length();
        if (number > 10 || number < 10) {
            log.warn("Enter 10 digit phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_10_DIGIT_PHONE_NUMBER);
        }
        int password = String.valueOf(account.getPassword()).length();
        if (password > 4 || password < 4) {
            log.warn("Enter 4 digit password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_4_DIGIT_Password);
        }

        Long num = account.getPhone();
        AccountTable accountTable = accountRepository.getUser(num);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        OtpTable otpTable = otpRepository.getUser(num);
        if (otpTable == null) {
            log.warn("Generate otp");
            throw new DuplicateKeyException(ResultInfoConstants.GENERATE_OTP);
        }

        if (account.getOtp() != otpTable.getOtp()) {
            log.warn("Put valid otp");
            throw new DuplicateKeyException(ResultInfoConstants.PUT_VALID_OTP);
        }
        otpRepository.deleteById(num);

        AccountTable newAccount = account.toAccountTable();
        newAccount.setCreatedAt(accountTable.getCreatedAt());

        int i = newAccount.getCount() + 1;
        newAccount.setCount(i);
        accountRepository.save(newAccount);
        return newAccount.getPhone();
    }

    public Long logIn(Account account) {
        if (account.getPhone() == null || account.getPassword() == 0) {
            log.warn("Enter phone number and password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_PHONE_NUMBER_AND_PASSWORD);
        }
        int number = String.valueOf(account.getPhone()).length();
        if (number > 10 || number < 10) {
            log.warn("Enter 10 digit phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_10_DIGIT_PHONE_NUMBER);
        }
        int password = String.valueOf(account.getPassword()).length();
        if (password > 4 || password < 4) {
            log.warn("Enter 4 digit password");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_4_DIGIT_Password);
        }

        Long num = account.getPhone();
        AccountTable accountTable = accountRepository.getUser(num);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        if (accountTable.getPassword() != account.getPassword()) {
            log.warn("Enter valid credential");
            throw new AccountNotFoundException(ResultInfoConstants.ENTER_VALID_CREDENTIAL);
        }
        accountTable.setLogin(true);
        accountRepository.save(accountTable);
        return accountTable.getPhone();
    }

    public Long logOut(Account account) {
        Long num = account.getPhone();
        AccountTable accountTable = accountRepository.getUser(num);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }
        accountTable.setLogin(false);
        accountRepository.save(accountTable);
        return accountTable.getPhone();
    }


}
