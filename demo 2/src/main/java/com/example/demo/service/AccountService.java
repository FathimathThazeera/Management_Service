package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Validator;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.AccountTable;
import com.example.demo.repository.table.OtpRepository;
import com.example.demo.repository.table.OtpTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {
    private final Validator validator;

    private final AccountRepository accountRepository;

    private final OtpRepository otpRepository;


    public Long create(Long phone, int password, int otp) {
        validator.validatePassword(password);

        validator.validatePhoneNumber(phone);

        List<Long> accountTable = accountRepository.getAllPhoneNumber();
        if (accountTable.contains(phone)) {
            log.warn("ACCOUNT_ALREADY_EXISTING");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_ALREADY_EXISTING);
        }
        OtpTable otpTable = otpRepository.getUser(phone);
        if (otpTable == null) {
            log.warn("Generate otp");
            throw new DuplicateKeyException(ResultInfoConstants.GENERATE_OTP);
        }
        if (otp != otpTable.getOtp()) {
            log.warn("Put valid otp");
            throw new DuplicateKeyException(ResultInfoConstants.PUT_VALID_OTP);
        }
        otpRepository.deleteById(phone);
        AccountTable newAccountTable = new AccountTable(phone, password);
        return accountRepository.save(newAccountTable).getPhone();

    }

    public Long resetPassword(Long phone, int password, int otp) {

        validator.validatePassword(password);

        validator.validatePhoneNumber(phone);

        AccountTable accountTable = accountRepository.getUser(phone);
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        OtpTable otpTable = otpRepository.getUser(phone);
        if (otpTable == null) {
            log.warn("Generate otp");
            throw new DuplicateKeyException(ResultInfoConstants.GENERATE_OTP);
        }

        if (otp != otpTable.getOtp()) {
            log.warn("Put valid otp");
            throw new DuplicateKeyException(ResultInfoConstants.PUT_VALID_OTP);
        }
        otpRepository.deleteById(phone);

        AccountTable newAccount = new AccountTable(phone, password);
        newAccount.setCreatedAt(accountTable.getCreatedAt());

        int i = newAccount.getCount() + 1;
        newAccount.setCount(i);
        accountRepository.save(newAccount);
        return newAccount.getPhone();
    }


}
