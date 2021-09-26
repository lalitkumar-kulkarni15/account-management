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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
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
    public void transfersFundsSuccessfully(){

        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        // Step 1 : Add source account
        HttpEntity<Account> sourceBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<AccountResponse> sourceAccountResponse = restTemplate.exchange(url, HttpMethod.POST, sourceBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,sourceAccountResponse.getStatusCode());
        assertNotNull(sourceAccountResponse.getBody());

        // Step 2 : Add destination account
        final Account destinationBankAccountForTransferRequest = TestDataUtils.getDestinationBankAccountForTransferRequest();
        HttpEntity<Account> destinationBankAccountRequest = new HttpEntity<Account>(destinationBankAccountForTransferRequest, null);
        ResponseEntity<AccountResponse> destinationAccountResponse = restTemplate.exchange(url, HttpMethod.POST, destinationBankAccountRequest, AccountResponse.class);
        assertSame(HttpStatus.CREATED,destinationAccountResponse.getStatusCode());
        assertNotNull(destinationAccountResponse.getBody());

        // Step 3 : Fund transfer from source account to destination account
        if(Objects.nonNull(sourceAccountResponse.getBody()) && Objects.nonNull(destinationAccountResponse.getBody())){
            final Transaction transaction = TestDataUtils.getFundTransferRequest(sourceAccountResponse.getBody().getAccountNumber(), destinationAccountResponse.getBody().getAccountNumber());
            HttpEntity<Transaction> transactionRequest = new HttpEntity<Transaction>(transaction, null);
            ResponseEntity<Object> transactionResponse = restTemplate.exchange(url, HttpMethod.POST, transactionRequest, Object.class);

            assertSame(HttpStatus.CREATED,transactionResponse.getStatusCode());
            assertNotNull(transactionResponse.getBody());

            TransactionLogRequest transactionLogRequest = TestDataUtils.getTransactionLogRequest(sourceAccountResponse.getBody().getAccountNumber());
            HttpEntity<TransactionLogRequest> transactionLogReq = new HttpEntity<TransactionLogRequest>(transactionLogRequest, null);
            final String urlTransactionLog = TestDataUtils.createURLWithPort("/account-management/transaction/log",host, port);
            ResponseEntity<TransactionWrapper> transactionList = restTemplate.exchange(urlTransactionLog, HttpMethod.POST, transactionLogReq, TransactionWrapper.class);

            assertSame(HttpStatus.CREATED,transactionResponse.getStatusCode());
            assertNotNull(transactionResponse.getBody());
        }

        // Step 4 : Fetch transaction log




    }

}
