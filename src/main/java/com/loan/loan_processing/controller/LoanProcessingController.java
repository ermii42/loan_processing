package com.loan.loan_processing.controller;

import com.loan.loan_processing.dto.TariffDTO;
import com.loan.loan_processing.model.Tariff;
import com.loan.loan_processing.service.TariffService;
import com.loan.loan_processing.service.impl.TariffServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan-service")
public class LoanProcessingController {
    private final TariffService tariffService;
    @GetMapping("/getTariffs")
    public ResponseEntity<List<Tariff>> getTariffs(){
        List<Tariff> tariffs = tariffService.getAllTariffs();
        return ResponseEntity.ok(tariffs);
    }

    @PostMapping("/saveTariff")
    public void saveTariff(@RequestBody TariffDTO tariffDTO){
        tariffService.saveTariff(tariffDTO);
    }

}
