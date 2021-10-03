package br.com.alura.walletapi.application.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(String error, String message, Integer status, String path) {
        super(error, message, status, path);
    }

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }

}
