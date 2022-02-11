package com.example.demo.exceptions;

import com.example.demo.response.ResultInfo;
import lombok.Data;

@Data
public class AccountNotFoundException extends RuntimeException {
    private final ResultInfo result;
}
