package com.account.management.utils;

import com.account.management.model.Account;
import com.account.management.model.Transaction;
import com.account.management.model.TransactionLogRequest;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataUtils {

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
                .build();

    }

    public static TransactionLogRequest getTransactionLogRequest(int accountNumber){

        return TransactionLogRequest.builder()
                .accountNumber(accountNumber)
                .fromDateTime(LocalDateTime.now().minusDays(1))
                .toDateTime(LocalDateTime.now().plusDays(1))
                .order("DESC")
                .pageNumber(1)
                .pageSize(10)
                .build();
    }

    public static Transaction getFundTransferRequest(int fromAccountNumber, int toAccountNumber){

        return Transaction.builder()
                .amount(1)
                .fromAccountNumber(fromAccountNumber)
                .toAccountNumber(toAccountNumber)
                .transferComments("Birthday gift")
                .build();
    }

    public static HttpHeaders getHttpHeader(){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypeList);
        return httpHeaders;
    }

    public static String createURLWithPort(final String uri, final String host,
                                           final String port) {

        return "http://" + host + ":" + port + uri;
    }
}
