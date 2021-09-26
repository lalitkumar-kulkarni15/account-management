package com.account.management.controller;

import com.account.management.model.Account;
import com.account.management.model.AccountResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Account management API", value = "")
public interface IAccountController {

    @ApiOperation(value = "Open new bank account", notes = "This API creates a new bank account")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Bank account created successfully.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    AccountResponse create(@Valid @RequestBody Account accountRequest);

}
