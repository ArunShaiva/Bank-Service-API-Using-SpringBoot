package com.project.service;

import com.project.dto.Accountdto;
import com.project.repository.AccountRepository;
import com.project.service.impl.AccountServiceImpl;

import java.util.List;


public interface AccountService {

    Accountdto createAccount(Accountdto accountdto);
    Accountdto getAccountById(Long id);

    Accountdto deposit(Long id,Double amount);

    Accountdto withdraw(Long id,Double amount);

    List<Accountdto> getAllAccounts();

    void deleteAccount(Long id);




}
