package br.com.fiap.infrastructure.exceptions;

public class InfraestruturaException extends  RuntimeException {

    public InfraestruturaException(String message) {
        super(message);
    }

    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }
}
