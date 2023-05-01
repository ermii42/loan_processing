package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TariffDTO {
    private String type;
    private String interestRate;
}
