package com.loan.loan_processing.config;

import com.loan.loan_processing.model.LoanOrder;
import com.loan.loan_processing.repository.LoanOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ApplicationConsideration {
    private final LoanOrderRepository loanOrderRepository;

    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void scheduleFixedDelayTask(){
        List<LoanOrder> orders = loanOrderRepository.getInProgressOrders().orElseThrow();
        for(LoanOrder order: orders){
            if((int) (Math.random() * 2) == 1){
                loanOrderRepository.updateOrder(
                        "APPROVED",
                        new Timestamp(System.currentTimeMillis()),
                        order.getId());
            }
            else{
                loanOrderRepository.updateOrder(
                        "REFUSED",
                        new Timestamp(System.currentTimeMillis()),
                        order.getId());
            }
        }
    }
}
