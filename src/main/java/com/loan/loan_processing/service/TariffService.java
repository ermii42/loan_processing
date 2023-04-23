package com.loan.loan_processing.service;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.Tariff;
import com.loan.loan_processing.model.response.DataResponse;

import java.util.List;

public interface TariffService {
    DataResponse getAllTariffs();
    void saveTariff(TariffDTO tariffDTO);
}
