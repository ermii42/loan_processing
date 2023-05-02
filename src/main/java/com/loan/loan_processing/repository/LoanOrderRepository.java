package com.loan.loan_processing.repository;

import com.loan.loan_processing.dto.LoanOrderDeleteDTO;
import com.loan.loan_processing.dto.LoanOrderSaveDTO;
import com.loan.loan_processing.model.LoanOrder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface LoanOrderRepository {
    List<LoanOrder> getLoanOrderByUserId(long user_id);
    void saveLoanOrder(LoanOrderSaveDTO loanOrderSaveDTO);
    Boolean isOrderIDExists(String order_id);
    LoanOrder getOrderByOrderId(String order_id);
    Optional<List<LoanOrder>> getAllOrders();

    Boolean isUserIdAndOrderIdExists(LoanOrderDeleteDTO loanOrderDeleteDTO);
    Boolean isPossibleToDelete(LoanOrderDeleteDTO loanOrderDeleteDTO);
    void deleteOrder(LoanOrderDeleteDTO loanOrderDeleteDTO);
    Optional<List<LoanOrder>> getInProgressOrders();
    void updateOrder(String status, Timestamp time_update, long id);
}
