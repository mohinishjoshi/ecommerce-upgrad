package com.upgrad.ecommerce.utils;

import lombok.Data;

import java.util.List;


@Data
public class ErrorResponse {

    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;
}
