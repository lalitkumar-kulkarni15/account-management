package com.account.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate implements Serializable {

    private static final long SERIAL_VERSION_UUID = 1L;

    private double result;

}
