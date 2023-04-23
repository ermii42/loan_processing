package com.loan.loan_processing.model.response;

import com.loan.loan_processing.model.Tariff;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TariffResponse {
    private List<Tariff> tariffs;
}
