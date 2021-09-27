package com.account.management.service;

import com.account.management.entity.TransactionEntity;
import com.account.management.exception.AccountManagementException;
import com.account.management.exception.AccountManagementValidationException;
import com.account.management.model.Account;
import com.account.management.model.ExchangeRate;
import com.account.management.model.Transaction;
import com.account.management.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Transactional
    public void transfer(Transaction transferRequest) {

        final Optional<Account> sourceAccount = bankAccountService.get(transferRequest.getFromAccountNumber());
        final Optional<Account> destinationAccount = bankAccountService.get(transferRequest.getToAccountNumber());

        // Step 1 : Deduct amount from source account.
        deductAmountFromSourceAccount(transferRequest, sourceAccount);

        // Step 2 : Add the transferred amount to the destination account.
        addAmountToDestinationAccount(transferRequest, sourceAccount, destinationAccount);

        // Step 3 : Insert transaction in the transaction log table
        insertTransactionInLogTable(transferRequest);

    }

    private void insertTransactionInLogTable(Transaction transferRequest) {
        final TransactionEntity transactionEntity = modelMapper.map(transferRequest, TransactionEntity.class);
        transactionEntity.setTransactionDateTime(new Date());
        transactionRepository.save(transactionEntity);
    }

    private void addAmountToDestinationAccount(Transaction transferRequest, Optional<Account> sourceAccount, Optional<Account> destinationAccount) {

        destinationAccount.ifPresentOrElse(account -> {

            Optional<ExchangeRate> exchangeRate = Optional.ofNullable(currencyConversionService.getConvertedAmount(sourceAccount.get().getCurrency(), account.getCurrency(), transferRequest.getAmount()));

            if (exchangeRate.isPresent()) {
                final double updatedDestinationAccountBalance = account.getAcctBalance() + exchangeRate.get().getResult();
                bankAccountService.updateAmountInAccount(updatedDestinationAccountBalance, account.getAccountNumber());
            } else {
                throw new AccountManagementException("Error while fetching the exchange rate.");
            }

        }, () -> {
            throw new AccountManagementValidationException("Destination account number is invalid");
        });
    }

    private void deductAmountFromSourceAccount(Transaction transferRequest, Optional<Account> sourceAccount) {

        sourceAccount.ifPresentOrElse(account -> {

            validateSufficientAccountBalance(transferRequest.getAmount(), account);
            final double updatedSourceAccountBalance = account.getAcctBalance() - transferRequest.getAmount();
            bankAccountService.updateAmountInAccount(updatedSourceAccountBalance, account.getAccountNumber());
        }, () -> {
            throw new AccountManagementValidationException("Source account number is invalid");
        });
    }

    private void validateSufficientAccountBalance(double transferAmount, Account account) {
        if (account.getAcctBalance() < transferAmount) {
            throw new AccountManagementValidationException("Source account has insufficient balance.");
        }
    }

    public List<Transaction> getTransactions(int accountNumber, Date fromDateTime, Date toDateTime, Pageable pageable) {

        final List<TransactionEntity> transactionPage = transactionRepository.findTransactionLogs(fromDateTime, toDateTime,
                accountNumber, pageable);
        return modelMapper.map(transactionPage, new TypeToken<List<Transaction>>() {
        }.getType());
    }

}
