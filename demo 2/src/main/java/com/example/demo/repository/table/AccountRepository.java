package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountTable, Long> {
    @Query(value = "select * from account_table where phone = :phone ", nativeQuery = true)
    public AccountTable getUser(Long phone);

    @Query(value = "select phone from account_table", nativeQuery = true)
    public List<Long> getAllPhoneNumber();

}

