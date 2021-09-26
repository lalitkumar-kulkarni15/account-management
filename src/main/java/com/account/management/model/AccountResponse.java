package com.account.management.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountResponse extends ControlFields {

    @Builder
    public AccountResponse(int accountNumber, LocalDateTime createdDateTime){
        super(accountNumber, createdDateTime);
    }
}
