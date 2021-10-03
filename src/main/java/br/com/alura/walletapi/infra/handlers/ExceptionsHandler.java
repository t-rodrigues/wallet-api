package br.com.alura.walletapi.infra.handlers;

import br.com.alura.walletapi.application.exceptions.StandardError;
import br.com.alura.walletapi.application.exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {
        var error = new ValidationError(ex.getClass().toString(), ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
                request.getContextPath());

        ex.getFieldErrors().forEach(f -> error.addError(f.getField(), f.getDefaultMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception ex, HttpServletRequest request) {
        var error = getError(ex.getClass().toString(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI());

        return ResponseEntity.internalServerError().body(error);
    }

    private StandardError getError(String error, String message, Integer status, String path) {
        return new StandardError(error, message, status, path);
    }

}
