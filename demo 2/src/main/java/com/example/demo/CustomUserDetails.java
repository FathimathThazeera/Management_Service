package com.example.demo;

import com.example.demo.repository.table.AccountTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetails implements UserDetails {

    private AccountTable accountTable;

    public CustomUserDetails(AccountTable accountTable) {
        super();
        this.accountTable = accountTable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(null));
    }

    @Override
    public String getPassword() {
        return String.valueOf(accountTable.getPassword());
    }

    @Override
    public String getUsername() {
        return accountTable.getPhone().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
