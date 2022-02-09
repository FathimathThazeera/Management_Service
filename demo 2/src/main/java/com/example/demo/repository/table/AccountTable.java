package com.example.demo.repository.table;

import com.example.demo.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "account_table")
@NoArgsConstructor
public class AccountTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phone;
    private int password;
    //private int otp;
    private int count;
    // private boolean login;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public AccountTable(Long phone, int password) {
        this.phone = phone;
        this.password = password;
    }

    public Account toAccount() {
        return new Account(this.id, this.phone, this.password, this.count);
    }
}
