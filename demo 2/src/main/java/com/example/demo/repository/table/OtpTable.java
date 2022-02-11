package com.example.demo.repository.table;

import com.example.demo.entity.Otp;
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
@Table(name = "otp_table")
@NoArgsConstructor
public class OtpTable {
    @Id
    private Long phone;
    private int otp;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;


    public OtpTable(Long phone, int otp) {
        this.phone = phone;
        this.otp = otp;
    }

    public Otp toOtp() {
        return new Otp(this.phone, this.otp);
    }
}

