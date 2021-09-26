package com.account.management.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {

    private int fromAccountNumber;

    private int toAccountNumber;

    private int amount;

    private String transferComments;

    private Date transactionDateTime;

}
