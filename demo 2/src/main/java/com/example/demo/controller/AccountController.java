package com.example.demo.controller;

import com.example.demo.CustomUserDetailsService;
import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.entity.Account;
import com.example.demo.model.JwtResponse;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.AccountService;
import com.example.demo.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {


    private final JWTUtility jwtUtility;


    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final AccountService accountService;


    private final ObjectMapper objectMapper;

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

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody @Valid Account account) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            account.getPhone(),
                            account.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = customUserDetailsService.loadUserByUsername(account.getPhone().toString());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);

    }
}
