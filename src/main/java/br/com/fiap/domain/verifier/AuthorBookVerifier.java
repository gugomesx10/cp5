package br.com.fiap.domain.verifier;

import br.com.fiap.domain.exceptions.ClienteConsistenceError;
import br.com.fiap.domain.model.Book;

public interface AuthorBookVerifier {
    void verificar(Book book) throws ClienteConsistenceError;
}
