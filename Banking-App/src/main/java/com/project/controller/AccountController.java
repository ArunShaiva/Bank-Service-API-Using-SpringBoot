package com.project.controller;

import com.project.dto.Accountdto;
import com.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //This class has only one constructor and spring will inject the dependecies automatically

    @PostMapping
    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto) {
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }

    //Get Account Rest Api
    @GetMapping("/{id}")
    public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id) {
        Accountdto accountdto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountdto);
    }

    //Deposit Rest api

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        Accountdto accountdto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountdto);

    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Accountdto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        Accountdto accountdto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountdto);

    }

    //Get all accounts

    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts() {
        List<Accountdto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted Succesfully");
    }

}
