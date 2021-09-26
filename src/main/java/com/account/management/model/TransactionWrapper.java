package com.account.management.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TransactionWrapper {

    private List<Transaction> transactionList;

}
