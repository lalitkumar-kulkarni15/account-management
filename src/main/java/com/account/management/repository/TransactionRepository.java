package com.account.management.repository;

import com.account.management.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Query(value = "from TransactionEntity t where (t.transactionDateTime between :fromDateTime and :toDateTime) AND ((t.fromAccountNumber) =:accountNumber OR (t.toAccountNumber) =:accountNumber)")
    List<TransactionEntity> findTransactionLogs(Date fromDateTime, Date toDateTime, int accountNumber, Pageable pageable);
}
