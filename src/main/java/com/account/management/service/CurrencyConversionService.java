package com.account.management.service;

import com.account.management.model.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "currencyConversionService", url = "${currency.conversion.host}")
public interface CurrencyConversionService {

    @GetMapping("/convert")
    ExchangeRate getConvertedAmount(@RequestParam(name = "from") String from, @RequestParam(name = "to") String to, @RequestParam(name = "amount") double amount);

}
