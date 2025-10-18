package br.com.fiap.infrastructure.exceptions;

public class InfraestruturaException extends RuntimeException {

    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }
}
