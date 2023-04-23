package com.loan.loan_processing.repository.imp;

import com.loan.loan_processing.model.Tariff;
import com.loan.loan_processing.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TariffRepositoryImpl implements TariffRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_ALL_REQUEST = "SELECT * FROM tariff";
    @Override
    public List<Tariff> getAllTariffs() {
        return jdbcTemplate.query(
                FIND_ALL_REQUEST,
                new BeanPropertyRowMapper<>(Tariff.class));
    }
}
