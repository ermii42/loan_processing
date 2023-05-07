package com.loan.loan_processing.repository.imp;

import com.loan.loan_processing.dto.LoanOrderDeleteDTO;
import com.loan.loan_processing.dto.LoanOrderSaveDTO;
import com.loan.loan_processing.model.LoanOrder;
import com.loan.loan_processing.repository.LoanOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoanOrderRepositoryImpl implements LoanOrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_BY_USER_ID_REQUEST = "SELECT * FROM loan_order WHERE user_id=?";
    private static final String SAVE_REQUEST =
            "INSERT INTO loan_order(order_id, user_id, tariff_id, credit_rating, status, time_insert, time_update) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ORDER_BY_ORDER_ID_REQUEST = "SELECT EXISTS (SELECT * FROM loan_order WHERE order_id=?)";
    private static final String FIND_ALL_REQUEST = "SELECT * FROM loan_order";

    private static final String FIND_ORDER_BY_USER_ID_AND_ORDER_ID_REQUEST =
            "SELECT EXISTS (SELECT * FROM loan_order WHERE user_id=? AND order_id=?)";

    private static final String FIND_ORDER_BY_USER_ID_AND_ORDER_ID_WHERE_IN_PROGRESS_REQUEST =
            "SELECT EXISTS (SELECT * FROM loan_order WHERE user_id=? AND order_id=? AND status='IN_PROGRESS')";
    private static final String DELETE_ORDER_REQUEST = "DELETE FROM loan_order WHERE user_id=? AND order_id=?";
    private static final String FIND_IN_PROGRESS_REQUEST = "SELECT * FROM loan_order WHERE status='IN_PROGRESS'";
    private static final String UPDATE_TIME_REQUEST = "UPDATE loan_order SET status=?, time_update=? WHERE id=?";

    @Override
    public List<LoanOrder> getLoanOrderByUserId(long userId) {
        return jdbcTemplate.query(
                FIND_BY_USER_ID_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class),
                userId);
    }

    @Override
    public void saveLoanOrder(LoanOrderSaveDTO loanOrderSaveDTO) {
        jdbcTemplate.update(
                SAVE_REQUEST,
                loanOrderSaveDTO.getOrderId(),
                loanOrderSaveDTO.getUserId(),
                loanOrderSaveDTO.getTariffId(),
                loanOrderSaveDTO.getCreditRating(),
                loanOrderSaveDTO.getStatus(),
                loanOrderSaveDTO.getTimeInsert(),
                loanOrderSaveDTO.getTimeUpdate());
    }

    @Override
    public Boolean isOrderIDExists(String orderId) {
        return jdbcTemplate.queryForObject(
                FIND_ORDER_BY_ORDER_ID_REQUEST,
                Boolean.class,
                orderId);
    }

    @Override
    public LoanOrder getOrderByOrderId(String orderId) {
        List<LoanOrder> res = jdbcTemplate.query(
                FIND_ORDER_BY_ORDER_ID_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class),
                orderId);
        return res.get(0);
    }

    @Override
    public Optional<List<LoanOrder>> getAllOrders() {
        return Optional.of(jdbcTemplate.query(
                FIND_ALL_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class)));
    }

    @Override
    public Boolean isUserIdAndOrderIdExists(LoanOrderDeleteDTO loanOrderDeleteDTO) {
        return jdbcTemplate.queryForObject(
                FIND_ORDER_BY_USER_ID_AND_ORDER_ID_REQUEST,
                Boolean.class,
                loanOrderDeleteDTO.getUserId(),
                loanOrderDeleteDTO.getOrderId());
    }

    @Override
    public Boolean isPossibleToDelete(LoanOrderDeleteDTO loanOrderDeleteDTO) {
        return jdbcTemplate.queryForObject(
                FIND_ORDER_BY_USER_ID_AND_ORDER_ID_WHERE_IN_PROGRESS_REQUEST,
                Boolean.class,
                loanOrderDeleteDTO.getUserId(),
                loanOrderDeleteDTO.getOrderId());
    }

    @Override
    public void deleteOrder(LoanOrderDeleteDTO loanOrderDeleteDTO) {
        jdbcTemplate.update(
                DELETE_ORDER_REQUEST,
                loanOrderDeleteDTO.getUserId(),
                loanOrderDeleteDTO.getOrderId());
    }

    @Override
    public Optional<List<LoanOrder>> getInProgressOrders() {
        return Optional.of(jdbcTemplate.query(
                FIND_IN_PROGRESS_REQUEST,
                new BeanPropertyRowMapper<>(LoanOrder.class)));
    }

    @Override
    public void updateOrder(String status, Timestamp time_update, long id) {
        jdbcTemplate.update(
                UPDATE_TIME_REQUEST,
                status,
                time_update,
                id
        );
    }

}
