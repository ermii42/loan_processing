package com.loan.loan_processing.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanErrorResponse {
    String code;
    String message;
}
