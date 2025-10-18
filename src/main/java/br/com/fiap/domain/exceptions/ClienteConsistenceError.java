package br.com.fiap.domain.exceptions;

public class ClienteConsistenceError extends RuntimeException {

    public ClienteConsistenceError(String message) {
        super(message);
    }
}
