package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoanOrderDTO {
    private long userId;
    private long tariffId;
}
