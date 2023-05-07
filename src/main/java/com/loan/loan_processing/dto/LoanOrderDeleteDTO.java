package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanOrderDeleteDTO {
    private long userId;
    private String orderId;
}
