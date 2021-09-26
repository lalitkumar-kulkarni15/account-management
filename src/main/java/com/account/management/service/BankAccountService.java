package com.account.management.service;

import com.account.management.model.Account;
import com.account.management.model.AccountResponse;
import java.util.Optional;

public interface BankAccountService {

    AccountResponse create(Account accountRequest);

    Account update(Account accountRequest);

    Optional<Account> get(int accountNumber);

    void updateAmountInAccount(double amount, int accountNumber);

}
