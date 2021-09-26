package com.account.management.model;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionLogRequest {

    private int accountNumber;

    private LocalDateTime fromDateTime;

    private LocalDateTime toDateTime;

    private int pageNumber;

    private int pageSize;

    private String order;

}
