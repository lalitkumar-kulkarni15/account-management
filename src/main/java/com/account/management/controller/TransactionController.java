package com.account.management.controller;

import com.account.management.model.Transaction;
import com.account.management.model.TransactionLogRequest;
import com.account.management.model.TransactionWrapper;
import com.account.management.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TransactionController implements ITransactionController {

    private final FundService fundService;

    public TransactionController(FundService fundService){
        this.fundService = fundService;
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Transaction transaction){
        fundService.transfer(transaction);
    }

    @PostMapping("/transaction/log")
    public TransactionWrapper getTransactions(@RequestBody TransactionLogRequest transactionLogRequest){
        return new TransactionWrapper(fundService.getTransactions(transactionLogRequest));
    }

    @GetMapping("/transaction/log")
    public TransactionWrapper getTransactions(@PathVariable("accountNumber") int accountNumber, @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
                                              Pageable pageable) {

        TransactionLogRequest transactionLogRequest = TransactionLogRequest.builder()
                .accountNumber(accountNumber)
                .
                .
        return new TransactionWrapper(fundService.getTransactions(transactionLogRequest));
    }

}
