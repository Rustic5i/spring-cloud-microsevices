package com.javastart.account.service;

import com.javastart.account.entity.Account;
import com.javastart.account.exception.AccountNotFoundException;
import com.javastart.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getAccountById(Long id){
        return repository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Unable to find account with id: "+id));
    }

    public Long create(String name, String email, String phone, List<Long> bills){
        Account account = new Account(name,email,phone, OffsetDateTime.now(),bills);
        return repository.save(account).getAccountId();
    }

    public Account update(Long id,String name, String email, String phone, List<Long> bills){
        Account account = new Account();
        account.setAccountId(id);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setBills(bills);
        return repository.save(account);
    }

    public Account delete(Long id){
        Account deleteAccount = getAccountById(id);
        repository.deleteById(id);
        return deleteAccount;
    }
}
