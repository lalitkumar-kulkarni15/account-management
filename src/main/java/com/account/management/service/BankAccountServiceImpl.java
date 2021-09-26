package com.account.management.service;

import com.account.management.entity.AccountEntity;
import com.account.management.model.Account;
import com.account.management.model.AccountResponse;
import com.account.management.repository.BankAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ModelMapper modelMapper){
        this.bankAccountRepository=bankAccountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountResponse create(Account accountRequest) {
        final AccountEntity accountRequestEntity = saveOrUpdate(accountRequest);
        return  modelMapper.map(accountRequestEntity, AccountResponse.class);
    }

    @Override
    public Account update(Account accountRequest) {
        final AccountEntity accountUpdated =  saveOrUpdate(accountRequest);
        return  modelMapper.map(accountUpdated, Account.class);
    }

    private AccountEntity saveOrUpdate(Account accountRequest) {
        final AccountEntity accountRequestEntity = modelMapper.map(accountRequest, AccountEntity.class);
        bankAccountRepository.save(accountRequestEntity);
        return accountRequestEntity;
    }

    @Override
    public Optional<Account> get(int accountNumber) {
        final Optional<AccountEntity> accountEntity = bankAccountRepository.findById((accountNumber));
        return accountEntity.map(entity -> modelMapper.map(entity, Account.class));
    }

    @Override
    public void updateAmountInAccount(double amount, int accountNumber){
        bankAccountRepository.updateBalanceInAccount(amount, accountNumber);
    }

}
