package com.loan.loan_processing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table(name = "tariff")
public class Tariff {
    private long id;
    private String type;
    private String interest_rate;
}
