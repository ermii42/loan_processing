package com.loan.loan_processing.service;

import com.loan.loan_processing.dto.LoanOrderDTO;

public interface LoanOrderService {
    String applicationSubmission(LoanOrderDTO loanOrderDTO);
}
