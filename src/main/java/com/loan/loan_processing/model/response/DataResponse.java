package com.loan.loan_processing.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataResponse<T> {
    private T data;
}
