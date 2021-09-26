package com.account.management.model;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account extends ControlFields implements Serializable {

    private static final long SERIAL_VERSION_UUID = 1L;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private Date dob;

    @NotNull
    private String mobNo;

    @NotNull
    private String addressLineOne;

    private String addressLineTwo;

    private int pinCode;

    @NotNull
    private String nomineeName;

    @NotNull
    private String nomineeMobNum;

    @NotNull
    private String currency;

    private double acctBalance;

    @Builder
    public Account(int accountNumber, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String createdBy, String updatedBy, String status,
                   String firstName, String middleName, String lastName, Date dob, String mobNo, String addressLineOne, String addressLineTwo, int pinCode,
                   String nomineeName, String nomineeMobNum, String currency, double acctBalance) {
        super(accountNumber, createdDateTime, updatedDateTime, createdBy, updatedBy, status);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.mobNo = mobNo;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.pinCode = pinCode;
        this.nomineeName = nomineeName;
        this.nomineeMobNum = nomineeMobNum;
        this.currency = currency;
        this.acctBalance = acctBalance;
    }

}
