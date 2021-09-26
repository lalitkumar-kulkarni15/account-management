package com.account.management.controller;

import com.account.management.model.Account;
import com.account.management.model.AccountResponse;
import com.account.management.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Slf4j
@RestController
public class AccountController implements IAccountController {

    private final BankAccountService bankAccountService;

    public AccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@RequestBody Account accountRequest){
        return  bankAccountService.create(accountRequest);
    }

    @PutMapping("/account")
    public Account update(@RequestBody Account accountRequest){
        return  bankAccountService.update(accountRequest);
    }

    @GetMapping("/account/{accountNumber}")
    public Account get(@PathVariable("accountNumber") int accountNumber){
        Optional<Account> account = bankAccountService.get(accountNumber);
        return account.orElse(null);
    }

}
