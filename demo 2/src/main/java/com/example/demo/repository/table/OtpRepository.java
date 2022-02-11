package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<OtpTable, Long> {
    @Query(value = "select * from otp_table where phone = :phone ", nativeQuery = true)
    public OtpTable getUser(Long phone);
}
