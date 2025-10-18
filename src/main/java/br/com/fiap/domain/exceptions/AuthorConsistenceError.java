package br.com.fiap.domain.exceptions;

public class AuthorConsistenceError extends Exception {

    public AuthorConsistenceError() {
        super();
    }

    public AuthorConsistenceError(String message) {
        super(message);
    }
}
