package com.totvs.contasservice.domain.exceptions;

public class CsvProcessingException extends RuntimeException {
    public CsvProcessingException(String message) {
        super(message);
    }
}
