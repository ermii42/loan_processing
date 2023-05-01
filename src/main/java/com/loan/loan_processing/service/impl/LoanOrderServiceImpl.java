package com.loan.loan_processing.service.impl;

import com.loan.loan_processing.dto.LoanOrderDTO;
import com.loan.loan_processing.dto.LoanOrderSaveDTO;
import com.loan.loan_processing.exception.LoanException;
import com.loan.loan_processing.model.LoanOrder;
import com.loan.loan_processing.model.response.DataResponse;
import com.loan.loan_processing.model.response.OrderResponse;
import com.loan.loan_processing.repository.LoanOrderRepository;
import com.loan.loan_processing.repository.TariffRepository;
import com.loan.loan_processing.service.LoanOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanOrderServiceImpl implements LoanOrderService {
    private final LoanOrderRepository loanOrderRepository;
    private final TariffRepository tariffRepository;

    @Override
    public String applicationSubmission(LoanOrderDTO loanOrderDTO) {
        if (!tariffRepository.isTariffIDExists(loanOrderDTO.getTariff_id())) {
            throw new LoanException("TARIFF_NOT_FOUND", "Тариф не найден");
        }
        for (LoanOrder loanOrder : loanOrderRepository.getLoanOrderByUserId(loanOrderDTO.getUser_id())) {
            if (loanOrder.getTariff_id() == loanOrderDTO.getTariff_id()) {
                switch (loanOrder.getStatus()) {
                    case "IN_PROGRESS" -> throw new LoanException("LOAN_CONSIDERATION", "Заявка уже существует");
                    case "APPROVED" -> throw new LoanException("LOAN_ALREADY_APPROVED", "Заявка уже подтверждена");
                    case "REFUSED" -> {
                        long now = new Timestamp(System.currentTimeMillis()).getTime();
                        if (now / 1000 / 60 - loanOrder.getTime_update().getTime() / 1000 / 60 < 2) {
                            throw new LoanException("TRY_LATER", "Попробуйте позже");
                        }
                    }
                }
            }
        }
        String order_id = UUID.randomUUID().toString();
        double credit_rating = Math.random() * 0.8 + 0.1;
        String status = "IN_PROGRESS";
        Timestamp time_insert_and_update = new Timestamp(System.currentTimeMillis());
        LoanOrderSaveDTO loanOrderSaveDTO =
                new LoanOrderSaveDTO(order_id,
                        loanOrderDTO.getUser_id(), loanOrderDTO.getTariff_id(),
                        credit_rating, status,
                        time_insert_and_update,
                        time_insert_and_update);
        loanOrderRepository.saveLoanOrder(loanOrderSaveDTO);
        return order_id;
    }

    @Override
    public String getOrderStatus(String order_id) {
        if (!loanOrderRepository.isOrderIDExists(order_id)) {
            throw new LoanException("ORDER_NOT_FOUND", "Заявка не найдена");
        }
        return loanOrderRepository.getOrderByOrderId(order_id).getStatus();
    }

    @Override
    public DataResponse getAllOrders() {
        return new DataResponse(
                new OrderResponse(loanOrderRepository.getAllOrders().orElseThrow()));
    }
}
