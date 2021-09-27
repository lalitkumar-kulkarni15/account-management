package com.account.management.controller;

import com.account.management.AccountManagementApplication;
import com.account.management.model.*;
import com.account.management.utils.TestDataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {AccountManagementApplication.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class TransactionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @Value("${application.test.host}")
    private String host;

    @Test
    public void transfersFundsSuccessfully() throws ParseException {

        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        // Step 1 : Add source account
        ResponseEntity<AccountResponse> sourceAccountResponse = addSourceAccount(url);

        // Step 2 : Add destination account
        ResponseEntity<AccountResponse> destinationAccountResponse = addDestinationAccount(url);

        if(Objects.nonNull(sourceAccountResponse.getBody()) && Objects.nonNull(destinationAccountResponse.getBody())){

            // Step 3 : Create fund transfer from source account to destination account
            final Transaction transaction = TestDataUtils.getFundTransferRequest(sourceAccountResponse.getBody().getAccountNumber(), destinationAccountResponse.getBody().getAccountNumber());
            ResponseEntity<Object> transactionResponse = createNewTransaction(transaction);
            assertNotNull(transactionResponse);

        }

    }

    private ResponseEntity<TransactionWrapper> getTransactionLog(ResponseEntity<AccountResponse> sourceAccountResponse, Transaction transaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        Date toDate = getDate(transaction, 1);
        Date fromDate = getDate(transaction, -1);

        StringBuilder uriBuilder = new StringBuilder().append(TestDataUtils.createURLWithPort("",host,port)).append("/account-management/transaction/").append(sourceAccountResponse.getBody().getAccountNumber()).append("/").append(TestDataUtils.convertDate(fromDate)).append("/").append(TestDataUtils.convertDate(toDate));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uriBuilder.toString())
                .queryParam("page", 0)
                .queryParam("size", 10)
                .queryParam("sort", "transactionDateTime,DESC");

        ResponseEntity<TransactionWrapper> transactionList = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, TransactionWrapper.class);
        return transactionList;
    }

    private Date getDate(Transaction transaction, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(transaction.getTransactionDateTime());
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    private ResponseEntity<AccountResponse> addSourceAccountWithInsufficientBalance(String url){

        final Account accountRequest = TestDataUtils.getNewBankAccountRequestWithInsufficientBalance();
        HttpEntity<Account> sourceBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<AccountResponse> sourceAccountResponse = restTemplate.exchange(url, HttpMethod.POST, sourceBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,sourceAccountResponse.getStatusCode());
        assertNotNull(sourceAccountResponse.getBody());
        return sourceAccountResponse;
    }

    private ResponseEntity<AccountResponse> addSourceAccountWithInvalidSourceAcct(String url){

        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
        HttpEntity<Account> sourceBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<AccountResponse> sourceAccountResponse = restTemplate.exchange(url, HttpMethod.POST, sourceBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,sourceAccountResponse.getStatusCode());
        assertNotNull(sourceAccountResponse.getBody());
        return sourceAccountResponse;
    }

    private ResponseEntity<AccountResponse> addSourceAccount(String url){

        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
        HttpEntity<Account> sourceBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<AccountResponse> sourceAccountResponse = restTemplate.exchange(url, HttpMethod.POST, sourceBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,sourceAccountResponse.getStatusCode());
        assertNotNull(sourceAccountResponse.getBody());
        return sourceAccountResponse;
    }

    private ResponseEntity<AccountResponse> addDestinationAccount(String url){

        final Account destinationBankAccountForTransferRequest = TestDataUtils.getDestinationBankAccountForTransferRequest();
        HttpEntity<Account> destinationBankAccountRequest = new HttpEntity<Account>(destinationBankAccountForTransferRequest, null);
        ResponseEntity<AccountResponse> destinationAccountResponse = restTemplate.exchange(url, HttpMethod.POST, destinationBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,destinationAccountResponse.getStatusCode());
        assertNotNull(destinationAccountResponse.getBody());
        return destinationAccountResponse;
    }

    private ResponseEntity<Object> createNewTransaction(Transaction transaction){

        final String transactionUrl = TestDataUtils.createURLWithPort("/account-management/transaction",host, port);
        HttpEntity<Transaction> transactionRequest = new HttpEntity<Transaction>(transaction, null);
        ResponseEntity<Object> transactionResponse = restTemplate.exchange(transactionUrl, HttpMethod.POST, transactionRequest, Object.class);
        return transactionResponse;
    }

    @Test
    public void throwsInsufficientBalanceExceptionDuringFundTransfer() throws ParseException {

        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        // Step 1 : Add source account
        ResponseEntity<AccountResponse> sourceAccountResponse = addSourceAccountWithInsufficientBalance(url);

        // Step 2 : Add destination account
        ResponseEntity<AccountResponse> destinationAccountResponse = addDestinationAccount(url);

        if(Objects.nonNull(sourceAccountResponse.getBody()) && Objects.nonNull(destinationAccountResponse.getBody())){

            // Step 3 : Create fund transfer from source account to destination account
            final Transaction transaction = TestDataUtils.getFundTransferRequest(sourceAccountResponse.getBody().getAccountNumber(), destinationAccountResponse.getBody().getAccountNumber());
            ResponseEntity<Object> transactionResponse = createNewTransaction(transaction);

            assertSame(HttpStatus.BAD_REQUEST,transactionResponse.getStatusCode());

        }

    }

    @Test
    public void throwsBadRequestWhenInvalidSourceAccount() throws ParseException {

        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        // Step 1 : Add source account
        ResponseEntity<AccountResponse> sourceAccountResponse = addSourceAccount(url);

        // Step 2 : Add destination account
        ResponseEntity<AccountResponse> destinationAccountResponse = addDestinationAccount(url);

        if(Objects.nonNull(sourceAccountResponse.getBody()) && Objects.nonNull(destinationAccountResponse.getBody())){

            // Step 3 : Create fund transfer from source account to destination account
            final Transaction transaction = TestDataUtils.getFundTransferRequest(0, destinationAccountResponse.getBody().getAccountNumber());
            ResponseEntity<Object> transactionResponse = createNewTransaction(transaction);
            assertNotNull(transactionResponse);
            assertSame(HttpStatus.BAD_REQUEST,transactionResponse.getStatusCode());
        }

    }

    @Test
    public void throwsBadRequestWhenInvalidDestinationAccount() throws ParseException {

        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        // Step 1 : Add source account
        ResponseEntity<AccountResponse> sourceAccountResponse = addSourceAccount(url);

        // Step 2 : Add destination account
        ResponseEntity<AccountResponse> destinationAccountResponse = addDestinationAccount(url);

        if(Objects.nonNull(sourceAccountResponse.getBody()) && Objects.nonNull(destinationAccountResponse.getBody())){

            // Step 3 : Create fund transfer from source account to destination account
            final Transaction transaction = TestDataUtils.getFundTransferRequest(sourceAccountResponse.getBody().getAccountNumber(), 0);
            ResponseEntity<Object> transactionResponse = createNewTransaction(transaction);
            assertNotNull(transactionResponse);
            assertSame(HttpStatus.BAD_REQUEST,transactionResponse.getStatusCode());
        }

    }


}
