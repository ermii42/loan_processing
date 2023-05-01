package com.loan.loan_processing.repository.imp;

import com.loan.loan_processing.dto.LoanOrderSaveDTO;
import com.loan.loan_processing.model.LoanOrder;
import com.loan.loan_processing.repository.LoanOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoanOrderRepositoryImpl implements LoanOrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_BY_USER_ID_REQUEST = "SELECT * FROM loan_order WHERE user_id=?";
    private static final String SAVE_REQUEST =
            "INSERT INTO loan_order(order_id, user_id, tariff_id, credit_rating, status, time_insert, time_update) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ORDER_BY_ORDER_ID_REQUEST = "SELECT * FROM loan_order WHERE order_id=?";
    private static final String FIND_ALL_REQUEST = "SELECT * FROM loan_order";

    @Override
    public List<LoanOrder> getLoanOrderByUserId(long user_id) {
        return jdbcTemplate.query(
                FIND_BY_USER_ID_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class),
                user_id);
    }

    @Override
    public void saveLoanOrder(LoanOrderSaveDTO loanOrderSaveDTO) {
        jdbcTemplate.update(
                SAVE_REQUEST,
                loanOrderSaveDTO.getOrder_id(),
                loanOrderSaveDTO.getUser_id(),
                loanOrderSaveDTO.getTariff_id(),
                loanOrderSaveDTO.getCredit_rating(),
                loanOrderSaveDTO.getStatus(),
                loanOrderSaveDTO.getTime_insert(),
                loanOrderSaveDTO.getTime_update());
    }

    @Override
    public Boolean isOrderIDExists(String order_id) {
        List<LoanOrder> res = jdbcTemplate.query(
                FIND_ORDER_BY_ORDER_ID_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class),
                order_id);
        return res.size() != 0;
    }

    @Override
    public LoanOrder getOrderByOrderId(String order_id) {
        List<LoanOrder> res = jdbcTemplate.query(
                FIND_ORDER_BY_ORDER_ID_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class),
                order_id);
        return res.get(0);
    }

    @Override
    public Optional<List<LoanOrder>> getAllOrders() {
        return Optional.of(jdbcTemplate.query(
                FIND_ALL_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class)));
    }
}
