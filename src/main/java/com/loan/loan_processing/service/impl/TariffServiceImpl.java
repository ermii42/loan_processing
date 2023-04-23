package com.loan.loan_processing.service.impl;

import com.loan.loan_processing.model.Tariff;
import com.loan.loan_processing.repository.TariffRepository;
import com.loan.loan_processing.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;
    @Override
    public List<Tariff> getAllTariffs() {
        return tariffRepository.getAllTariffs();
    }
}
