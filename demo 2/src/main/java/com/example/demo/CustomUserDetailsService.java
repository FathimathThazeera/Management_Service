package com.example.demo;

import com.example.demo.constants.ResultInfoConstants;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.repository.table.AccountRepository;
import com.example.demo.repository.table.AccountTable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountTable accountTable = accountRepository.getUserByPhone(Long.parseLong(username));
        if (accountTable == null) {
            throw new AccountNotFoundException(ResultInfoConstants.ACCOUNT_NOT_FOUND);
        }

        return new CustomUserDetails(accountTable);
    }
}
