package com.alura.agenda.exception;

public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException() {
        super("Contato não encontrado");
    }

    public NotFoundException(String erro) {
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
