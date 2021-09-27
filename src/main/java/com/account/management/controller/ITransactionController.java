package com.account.management.controller;

import com.account.management.model.Transaction;
import com.account.management.model.TransactionWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Date;

@Api(tags = "Transaction management API", value = "")
public interface ITransactionController {

    @ApiOperation(value = "Create new fund transfer", notes = "This API transfers funds from source account to destination account")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Funds transferred successfully.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    void create(@RequestBody Transaction transaction);


    @ApiOperation(value = "Get the account statement of a particular account.", notes = "This API gets the account statement of a particular account by passing" +
            "account number, from date time and to date time. It additionally sorts the results ob the basis of date in ascending OR descending order . The results can" +
            "be paged by passing page number and page size in the request.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account statement fetched successfully.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    TransactionWrapper get(@PathVariable("accountNumber") int accountNumber, @PathVariable("fromDate") @DateTimeFormat(pattern = "dd-MMM-yyyy,HH:mm:ss") Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern = "dd-MMM-yyyy,HH:mm:ss") Date toDate,
                                  Pageable pageable);
}
