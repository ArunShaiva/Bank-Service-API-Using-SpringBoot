package com.project.service.impl;

import com.project.dto.Accountdto;
import com.project.entity.Account;
import com.project.mapper.AccountMapper;
import com.project.repository.AccountRepository;
import com.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Accountdto createAccount(Accountdto accountdto) {
        Account account = AccountMapper.mapToAccount(accountdto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public Accountdto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public Accountdto deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account does not exist"));

       double total= account.getBalance()+ amount;
       account.setBalance(total);
       Account savedAccount=accountRepository.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public Accountdto withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account does not exist"));

        if(account.getBalance() <amount) {
            throw new RuntimeException("Insufficeint Amount");
        }
        double total= account.getBalance() -amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<Accountdto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }
}
