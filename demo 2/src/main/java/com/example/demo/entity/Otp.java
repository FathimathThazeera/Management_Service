package com.example.demo.entity;

import com.example.demo.repository.table.OtpTable;
import lombok.Data;

@Data
public class Otp {
    private final Long phone;
    private final int otp;

    public OtpTable toOtpTable() {
        return new OtpTable(this.phone, this.otp);
    }
}
