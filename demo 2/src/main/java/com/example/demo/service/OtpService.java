package com.example.demo.service;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Otp;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.AccountTable;
import com.example.demo.repository.table.OtpRepository;
import com.example.demo.repository.table.OtpTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class OtpService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpRepository otpRepository;

    public int generateOtp(Otp otp) {

        AccountTable accountTable = accountRepository.getUser((otp.getPhone()));
        if (accountTable != null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_ALREADY_EXISTING);
        }

        OtpTable otpTable = otpRepository.getUser(otp.getPhone());
        if (otpTable != null) {
            log.warn("Otp already sent");
            throw new DuplicateKeyException(ResultInfoConstants.OTP_ALREADY_SENT);
        }

        if (otp.getPhone() == null) {
            log.warn("Enter phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_PHONE_NUMBER);
        }

        int len = String.valueOf(otp.getPhone()).length();
        if (len > 10 || len < 10) {
            log.warn("Enter 10 digit phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_10_DIGIT_PHONE_NUMBER);
        }

        otpRepository.save(otp.toOtpTable());
        OtpTable otpTable1 = otpRepository.getById(otp.getPhone());
        Random rnd = new Random();
        int newOtp = rnd.nextInt(999999);
        otpTable1.setOtp(newOtp);
        otpRepository.save(otpTable1);
        return newOtp;
    }

    public int forgotPassword(Otp otp) {
        AccountTable accountTable = accountRepository.getUser((otp.getPhone()));
        if (accountTable == null) {
            log.warn("Invalid phone number");
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        if (accountTable.getCount() == 1) {
            accountRepository.deleteById(otp.getPhone());
            log.warn("once password have been changed, Cant change for second time ");
            throw new DuplicateKeyException(ResultInfoConstants.CANT_SET_PASSWORD___RE_CREATE_ACCOUNT);
        }

        OtpTable otpTable = otpRepository.getUser(otp.getPhone());
        if (otpTable != null) {
            log.warn("Otp already sent");
            throw new DuplicateKeyException(ResultInfoConstants.OTP_ALREADY_SENT);
        }

        if (otp.getPhone() == null) {
            log.warn("Enter phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_PHONE_NUMBER);
        }

        int len = String.valueOf(otp.getPhone()).length();
        if (len > 10 || len < 10) {
            log.warn("Enter 10 digit phone number");
            throw new DuplicateKeyException(ResultInfoConstants.ENTER_10_DIGIT_PHONE_NUMBER);
        }

        otpRepository.save(otp.toOtpTable());
        OtpTable otpTable1 = otpRepository.getById(otp.getPhone());

        Random rnd = new Random();
        int newOtp = rnd.nextInt(999999);
        otpTable1.setOtp(newOtp);
        otpRepository.save(otpTable1);
        return newOtp;
    }
}

