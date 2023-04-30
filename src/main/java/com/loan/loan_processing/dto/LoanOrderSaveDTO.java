package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class LoanOrderSaveDTO {
    private String order_id;
    private long user_id;
    private long tariff_id;
    private double credit_rating;
    private String status;
    private Timestamp time_insert;
    private Timestamp time_update;
}
