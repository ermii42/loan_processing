package com.loan.loan_processing.model.response;

import com.loan.loan_processing.model.LoanOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private List<LoanOrder> orders;
}
