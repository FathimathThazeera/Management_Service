package com.example.demo.controller;

import com.example.demo.CustomUserDetailsService;
import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.model.JwtResponse;
import com.example.demo.response.ResponseWrapper;
import com.example.demo.service.AccountService;
import com.example.demo.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class AccountController {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final AccountService accountService;

    private final ObjectMapper objectMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> create(@RequestHeader("phone") Long phone, @RequestHeader("password") int password, @RequestHeader("otp") int otp) {
        log.info("Received a request to create account : {}", phone);
        return new ResponseWrapper(ResultInfoConstants.ACCOUNT_CREATED_SUCCESSFULLY, accountService.create(phone, password, otp));
    }

    @PostMapping("/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> resetPassword(@RequestHeader("phone") Long phone, @RequestHeader("password") int password, @RequestHeader("otp") int otp) {
        log.info("Received a request to reset otp :{}", phone);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, accountService.resetPassword(phone, password, otp));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticationToken(@RequestHeader("phone") Long phone, @RequestHeader("password") int password) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            phone, password
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = customUserDetailsService.loadUserByUsername(phone.toString());

        final String token =
                jwtUtility.generateToken(userDetails);

        log.info("Received a request to create token : {}", token);
        // return new ResponseWrapper(ResultInfoConstants.SUCCESS, new JwtResponse(token));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
