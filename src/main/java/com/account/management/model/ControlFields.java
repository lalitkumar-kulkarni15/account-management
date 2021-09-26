package com.account.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlFields implements Serializable  {

    private static final long SERIAL_VERSION_UUID = 1L;

    public ControlFields(int accountNumber, LocalDateTime createdDateTime){
        this.accountNumber = accountNumber;
        this.createdDateTime = createdDateTime;
    }

    private int accountNumber;

    private LocalDateTime createdDateTime;

    @JsonIgnore
    private LocalDateTime updatedDateTime;

    @NotNull
    private String createdBy;

    @JsonIgnore
    private String updatedBy;

    @NotNull
    private String status;

}
