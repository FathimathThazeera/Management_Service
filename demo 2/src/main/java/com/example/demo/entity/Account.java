package com.example.demo.entity;

import com.example.demo.repository.table.AccountTable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Data
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @NotNull
    private final Long phone;
    @NotNull
    private final int password;
    //private final int otp;
    private final int forgetPasswordCount;
    // private final boolean login;

    public AccountTable toAccountTable() {
        return new AccountTable(this.phone, this.password);
    }

}


