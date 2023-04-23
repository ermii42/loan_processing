package com.loan.loan_processing.service;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffs();
    void saveTariff(TariffDTO tariffDTO);
}
