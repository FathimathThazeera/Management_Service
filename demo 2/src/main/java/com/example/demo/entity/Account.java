package com.example.demo.entity;

import com.example.demo.repository.table.AccountTable;
import lombok.Data;

@Data
public class Account {

    private final Long phone;
    private final int password;
    private final int otp;
    private final int count;

    public AccountTable toAccountTable() {
        return new AccountTable(this.phone, this.password, this.otp, this.count);
    }

}


