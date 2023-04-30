package com.loan.loan_processing.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoanException extends RuntimeException{
    private String code;
    private String message;

}
