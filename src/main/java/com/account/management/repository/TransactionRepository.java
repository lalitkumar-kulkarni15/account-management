package com.account.management.repository;

import com.account.management.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    /*
    @Query(value = "SELECT TRANSACTION_ID, FROM_ACCT_NUM, TO_ACCT_NUM, TRANSACTION_AMT, TRANSFER_COMMENTS, TRANSACTION_DATE_TIME FROM ACCT_TRANSACTION " +
            " WHERE ((FROM_ACCT_NUM IN :accountNumber) OR (TO_ACCT_NUM IN :accountNumber)) AND TRANSACTION_DATE_TIME BETWEEN (:startDate AND :endDate) ", nativeQuery = true)
    Page<TransactionEntity> findTransactionLogs(@Param("accountNumber") int accountNumber, @Param("startDate") LocalDateTime startDate,
                                                @Param("endDate") LocalDateTime endDate, Pageable pageable);

     */

    /*@Query(value = "from TransactionEntity t where ((t.fromAccountNumber =:accountNumber) OR (t.toAccountNumber =:accountNumber)) and t.transactionDateTime BETWEEN :startDate AND :endDate")
    Page<TransactionEntity> findTransactionLogs(@Param("accountNumber") int accountNumber, @Param("startDate") LocalDateTime startDate,
                                                @Param("endDate") LocalDateTime endDate, Pageable pageable);
*/

    //@Query(value = "from TransactionEntity t")
    //Page<TransactionEntity> findTransactionLogs(@Param("accountNumber") int accountNumber, Pageable pageable);

    @Query(value = "from TransactionEntity t where (t.fromAccountNumber) =:accountNumber OR (t.toAccountNumber) =:accountNumber")
    List<TransactionEntity> findTransactionLogs(int accountNumber, Pageable pageable);
}
