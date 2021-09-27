package com.account.management.utils;

import com.account.management.model.Account;
import com.account.management.model.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataUtils {

    public static Account getNewBankAccountRequestWithInsufficientBalance() {

        return Account.builder()
                .firstName("Lalit")
                .middleName("S")
                .lastName("Kulkarni")
                .mobNo("+918765434219")
                .addressLineOne("Ausburg Avenue, Campal street, Panjim , Goa")
                .addressLineTwo("India")
                .dob(new Date())
                .currency("USD")
                .nomineeName("Paul Michael")
                .nomineeMobNum("+917865432199")
                .pinCode(768543)
                .createdBy("TEST_USER")
                .status("A")
                .acctBalance(0)
                .build();

    }

    public static Account getNewBankAccountRequest() {

        return Account.builder()
                .firstName("Lalit")
                .middleName("S")
                .lastName("Kulkarni")
                .mobNo("+918765434219")
                .addressLineOne("Ausburg Avenue, Campal street, Panjim , Goa")
                .addressLineTwo("India")
                .dob(new Date())
                .currency("USD")
                .nomineeName("Paul Michael")
                .nomineeMobNum("+917865432199")
                .pinCode(768543)
                .createdBy("TEST_USER")
                .status("A")
                .acctBalance(5)
                .build();

    }

    public static Account getBankAccountUpdateRequest(int accountNumber) {

        return Account.builder()
                .accountNumber(accountNumber)
                .firstName("Lalit")
                .middleName("S")
                .lastName("Kulkarni")
                .mobNo("+918765434219")
                .addressLineOne("Castle Avenue, Castle street, Mumbai , Maharashtra")
                .addressLineTwo("India")
                .dob(new Date())
                .currency("USD")
                .nomineeName("Paul Michael")
                .nomineeMobNum("+917865432199")
                .pinCode(7684356)
                .createdBy("TEST_USER")
                .status("A")
                .build();

    }

    public static Account getDestinationBankAccountForTransferRequest() {

        return Account.builder()
                .firstName("Mohit")
                .middleName("S")
                .lastName("Rhain")
                .mobNo("+918765434219")
                .addressLineOne("St. Michaels street")
                .addressLineTwo("India")
                .dob(new Date())
                .currency("INR")
                .nomineeName("Paul Michael")
                .nomineeMobNum("+917865432199")
                .pinCode(768543)
                .createdBy("TEST_USER")
                .status("A")
                .acctBalance(9)
                .build();

    }

    /*public static TransactionLogRequest getTransactionLogRequest(int accountNumber){

        return TransactionLogRequest.builder()
                .accountNumber(accountNumber)
                .fromDateTime(new Date())
                .toDateTime(new Date())
                .build();
    }*/

    public static Transaction getFundTransferRequest(int fromAccountNumber, int toAccountNumber){

        return Transaction.builder()
                .amount(1)
                .fromAccountNumber(fromAccountNumber)
                .toAccountNumber(toAccountNumber)
                .transferComments("Birthday gift")
                .transactionDateTime(new Date())
                .build();
    }

    public static Transaction getFundTransferRequestForInvalidSourceAccount(int fromAccountNumber, int toAccountNumber){

        return Transaction.builder()
                .amount(1)
                .fromAccountNumber(0)
                .toAccountNumber(toAccountNumber)
                .transferComments("Birthday gift")
                .transactionDateTime(new Date())
                .build();
    }

    public static String createURLWithPort(final String uri, final String host,
                                           final String port) {

        return "http://" + host + ":" + port + uri;
    }

    public static String convertDate(Date date){

        String pattern = "dd-MMM-yyyy,HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String stringDate = df.format(date);
        return stringDate;
    }
}
