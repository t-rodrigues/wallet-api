package br.com.alura.walletapi.application.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StandardError {

    private LocalDateTime timestamp = LocalDateTime.now();
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(String error, String message, Integer status, String path) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
    }

}
