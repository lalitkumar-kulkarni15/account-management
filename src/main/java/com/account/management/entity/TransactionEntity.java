package com.account.management.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ACCT_TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(name = "FROM_ACCT_NUM")
    private int fromAccountNumber;

    @Column(name = "TO_ACCT_NUM")
    private int toAccountNumber;

    @Column(name = "TRANSACTION_AMT")
    private int amount;

    @Column(name = "TRANSFER_COMMENTS")
    private String transferComments;

    @Column(name = "TX_DATE_TIME")
    private Date transactionDateTime;

}
