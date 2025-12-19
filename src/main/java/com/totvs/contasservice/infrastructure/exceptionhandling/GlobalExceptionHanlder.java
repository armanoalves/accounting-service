package com.totvs.contasservice.infrastructure.exceptionhandling;

import com.totvs.contasservice.domain.exceptions.ContaNaoEncontradaException;
import com.totvs.contasservice.domain.exceptions.CsvProcessingException;
import com.totvs.contasservice.domain.exceptions.UsuarioNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHanlder extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {
        String errors = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ErrorMessage> handleContaNaoEncontrada(ContaNaoEncontradaException ex) {
        ErrorMessage response = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CsvProcessingException.class)
    public ResponseEntity<ErrorMessage> handleCsvError(CsvProcessingException ex) {
        ErrorMessage response = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        ErrorMessage response = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
