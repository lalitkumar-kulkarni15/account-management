package com.account.management.controller;

import com.account.management.model.Transaction;
import com.account.management.model.TransactionWrapper;
import com.account.management.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Slf4j
@RestController
public class TransactionController implements ITransactionController {

    private final FundService fundService;

    public TransactionController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Transaction transaction) {
        fundService.transfer(transaction);
    }

    @GetMapping("/transaction/{accountNumber}/{fromDate}/{toDate}")
    public TransactionWrapper get(@PathVariable("accountNumber") int accountNumber, @PathVariable("fromDate") @DateTimeFormat(pattern = "dd-MMM-yyyy,HH:mm:ss") Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern = "dd-MMM-yyyy,HH:mm:ss") Date toDate,
                                  Pageable pageable) {

        return new TransactionWrapper(fundService.getTransactions(accountNumber, fromDate, toDate, pageable));
    }
}
