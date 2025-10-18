package br.com.fiap.domain.exceptions;

public class BookConsistenceError extends Exception {

    public BookConsistenceError() {
        super();
    }

    public BookConsistenceError(String message) {
        super(message);
    }
}
