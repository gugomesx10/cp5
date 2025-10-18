package br.com.fiap.application.exception;

public class AuthorUnsupportedOperation extends  RuntimeException {

    public AuthorUnsupportedOperation(String message) {
        super(message);
    }
}
