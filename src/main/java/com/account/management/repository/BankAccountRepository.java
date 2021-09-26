package com.account.management.repository;

import com.account.management.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<AccountEntity, Integer> {

    @Modifying
    @Query(value = "UPDATE BANK_ACCOUNTS AE SET AE.ACCT_BALANCE =:amount WHERE AE.ACCT_NUM =:accountNumber", nativeQuery = true)
    void updateBalanceInAccount(double amount, int accountNumber);

}
