package com.account.management.service;

import com.account.management.model.Transaction;
import com.account.management.model.TransactionLogRequest;

import java.util.List;

public interface FundService {

    void transfer(Transaction transaction);

    List<Transaction> getTransactions(TransactionLogRequest transactionLogRequest);

}
