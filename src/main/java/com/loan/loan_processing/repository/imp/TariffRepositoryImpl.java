package com.loan.loan_processing.repository.imp;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.Tariff;
import com.loan.loan_processing.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TariffRepositoryImpl implements TariffRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_ALL_REQUEST = "SELECT * FROM tariff";
    private static final String SAVE_REQUEST = "INSERT INTO tariff(type, interest_rate) VALUES (?, ?)";
    private static final String FIND_TARIFF_BY_ID_REQUEST = "SELECT EXISTS (SELECT * FROM tariff WHERE id=?)";

    @Override
    public Optional<List<Tariff>> getAllTariffs() {
        return Optional.of(jdbcTemplate.query(
                FIND_ALL_REQUEST,
                new BeanPropertyRowMapper<>(Tariff.class)));
    }

    @Override
    public void saveTariff(TariffDTO tariffDTO) {
        jdbcTemplate.update(
                SAVE_REQUEST,
                tariffDTO.getType(),
                tariffDTO.getInterestRate());
    }

    @Override
    public Boolean isTariffIDExists(long tariffId) {
        return jdbcTemplate.queryForObject(
                FIND_TARIFF_BY_ID_REQUEST,
                Boolean.class,
                tariffId);
    }

}
