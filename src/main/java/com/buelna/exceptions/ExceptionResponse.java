package com.buelna.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {

    private final String message;

    private final HttpStatus httpStatus;
}
