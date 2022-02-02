package com.example.demo.repository.table;

import com.example.demo.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "account_table")
@NoArgsConstructor
public class AccountTable {
    @Id
    private Long phone;
    private int password;
    private int otp;
    private int count;
    private boolean login;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public AccountTable(Long phone, int password, int otp, int count, boolean login) {
        this.phone = phone;
        this.password = password;
        this.otp = otp;
        this.count = count;
        this.login = login;
    }

    public Account toAccount() {
        return new Account(this.phone, this.password, this.otp, this.count, this.login);
    }
}
