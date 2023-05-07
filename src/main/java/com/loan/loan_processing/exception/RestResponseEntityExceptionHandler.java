package com.loan.loan_processing.exception;

import com.loan.loan_processing.model.response.ErrorResponse;
import com.loan.loan_processing.model.response.LoanErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = LoanException.class)
    protected ResponseEntity<ErrorResponse> handleConflict(LoanException loanException){
        ErrorResponse errorResponse = new ErrorResponse(new LoanErrorResponse(loanException.getCode(),
                loanException.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
