package com.loan.loan_processing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOrder {
    private long id;
    private String orderId;
    private long userId;
    private long tariffId;
    private double creditRating;
    private String status;
    private Timestamp timeInsert;
    private Timestamp timeUpdate;
}
