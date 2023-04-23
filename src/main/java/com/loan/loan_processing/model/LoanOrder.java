package com.loan.loan_processing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Table(name = "loan_order")
public class LoanOrder {
    private long id;
    private String order_id;
    private long user_id;
    private double credit_rating;
    private String status;
    private Timestamp time_insert;
    private Timestamp time_update;
}
