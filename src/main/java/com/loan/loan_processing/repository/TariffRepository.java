package com.loan.loan_processing.repository;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffRepository {
    Optional<List<Tariff>> getAllTariffs();
    void saveTariff(TariffDTO tariffDTO);
    Boolean isTariffIDExists(long tariff_id);
}
