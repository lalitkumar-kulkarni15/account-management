package com.account.management.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Validated
@Entity
@Table(name = "BANK_ACCOUNTS")
public class AccountEntity implements Serializable {

    private static final long SERIAL_VERSION_UUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCT_NUM")
    private int accountNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "MOB_NUM")
    private String mobNo;

    @Column(name = "ADDR_LINE_1")
    private String addressLineOne;

    @Column(name = "ADDR_LINE_2")
    private String addressLineTwo;

    @Column(name = "PIN_CODE")
    private int pinCode;

    @Column(name = "NOMINEE_NAME")
    private String nomineeName;

    @Column(name = "NOMINEE_MOB_NUM")
    private String nomineeMobNum;

    @Column(name = "ACCT_CURRENCY")
    private String currency;

    @Column(name = "ACCT_BALANCE")
    private double acctBalance;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "UPDATED_DATE_TIME")
    private LocalDateTime updatedDateTime;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "STATUS")
    private String status;

}
