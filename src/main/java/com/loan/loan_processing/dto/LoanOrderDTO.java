package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoanOrderDTO {
    private long user_id;
    private long tariff_id;
}
