package com.loan.loan_processing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class LoanOrderSaveDTO {
    private String orderId;
    private long userId;
    private long tariffId;
    private double creditRating;
    private String status;
    private Timestamp timeInsert;
    private Timestamp timeUpdate;
}
