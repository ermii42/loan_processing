package com.loan.loan_processing.controller;

import com.loan.loan_processing.dto.LoanOrderDTO;
import com.loan.loan_processing.model.response.DataResponse;
import com.loan.loan_processing.model.response.LoanOrderResponse;
import com.loan.loan_processing.service.LoanOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan-service")
public class LoanOrderController {
    private final LoanOrderService loanOrderService;
    @PostMapping("/order")
    public ResponseEntity<DataResponse> applicationSubmission(@RequestBody LoanOrderDTO loanOrderDTO) {
        String uuid = loanOrderService.applicationSubmission(loanOrderDTO);
        DataResponse dataResponse = new DataResponse<>(new LoanOrderResponse(uuid));
        return ResponseEntity.ok(dataResponse);
    }
}
