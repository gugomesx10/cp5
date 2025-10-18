package br.com.fiap.application.exception;

public class BookUnsupportedOperation extends  RuntimeException {

    public BookUnsupportedOperation(String message) {
        super(message);
    }
}
