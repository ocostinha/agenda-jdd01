package com.alura.agenda.exception;

public class BusinessException extends RuntimeException {
    private String message;

    public BusinessException() {
        super("Regra de negócio inválida");
    }

    public BusinessException(String erro) {
        this.message = erro;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
