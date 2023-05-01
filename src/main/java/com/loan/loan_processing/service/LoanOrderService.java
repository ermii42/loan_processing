package com.loan.loan_processing.service;

import com.loan.loan_processing.dto.LoanOrderDTO;
import com.loan.loan_processing.model.response.DataResponse;

public interface LoanOrderService {
    String applicationSubmission(LoanOrderDTO loanOrderDTO);
    String getOrderStatus(String order_id);

    DataResponse getAllOrders();
}
