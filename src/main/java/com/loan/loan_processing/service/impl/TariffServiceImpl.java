package com.loan.loan_processing.service.impl;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.response.DataResponse;
import com.loan.loan_processing.model.response.TariffResponse;
import com.loan.loan_processing.repository.TariffRepository;
import com.loan.loan_processing.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;

    @Override
    public DataResponse getAllTariffs() {
        return new DataResponse(
                new TariffResponse(tariffRepository.getAllTariffs().orElseThrow()));
    }

    @Override
    public void saveTariff(TariffDTO tariffDTO) {
        tariffRepository.saveTariff(tariffDTO);
    }
}
