package com.example.demo.controller;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Otp;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/otp")
@Slf4j
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Integer> generateOtp(@RequestBody @Valid Otp otp) {
        log.info("Generating Otp {}", otp);
        return new ResponseWrapper(ResultInfoConstants.OTP_GENERATED, otpService.generateOtp(otp));
    }

    @PostMapping("/forgotpassword")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Integer> forgotPassword(@RequestBody @Valid Otp otp) {
        log.info("Received a request to generate otp to change password :{}", otp);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, otpService.forgotPassword(otp));
    }
}
