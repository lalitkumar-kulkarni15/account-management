package com.account.management.service;

import com.account.management.model.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FundService {

    void transfer(Transaction transaction);

    List<Transaction> getTransactions(int accountNumber, Date fromDateTime, Date toDateTime, Pageable pageable);

}
