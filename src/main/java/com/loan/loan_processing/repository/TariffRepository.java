package com.loan.loan_processing.repository;

import com.loan.loan_processing.model.Tariff;

import java.util.List;

public interface TariffRepository {
    List<Tariff> getAllTariffs();
}
