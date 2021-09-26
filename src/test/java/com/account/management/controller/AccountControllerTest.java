package com.account.management.controller;

import com.account.management.AccountManagementApplication;
import com.account.management.model.Account;
import com.account.management.model.AccountResponse;
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
import java.util.Objects;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {AccountManagementApplication.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @Value("${application.test.host}")
    private String host;

    @Test
    public void createsNewBankAccountSuccessfully() {

        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        HttpEntity<Account> newBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, newBankAccountRequest, Object.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotSame(0, response.getBody());
    }

    @Test
    public void updatesBankAccountSuccessfully() {

        final Account accountRequest = TestDataUtils.getNewBankAccountRequest();
        final String url = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        HttpEntity<Account> newBankAccountRequest = new HttpEntity<Account>(accountRequest, null);
        ResponseEntity<AccountResponse> response = restTemplate.exchange(url, HttpMethod.POST, newBankAccountRequest, AccountResponse.class);

        Account accountUpdateRequest = null;

        if(Objects.nonNull(response) && Objects.nonNull(response.getBody())){
            accountUpdateRequest = TestDataUtils.getBankAccountUpdateRequest(response.getBody().getAccountNumber());
        }

        final String updateUrl = TestDataUtils.createURLWithPort("/account-management/account",host, port);

        HttpEntity<Account> bankAccountUpdateRequest = new HttpEntity<Account>(accountUpdateRequest, null);
        ResponseEntity<Object> updateResponse = restTemplate.exchange(updateUrl, HttpMethod.PUT, newBankAccountRequest, Object.class);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
    }


}
