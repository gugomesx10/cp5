package br.com.fiap.domain.verifier;

import br.com.fiap.domain.exceptions.ClienteConsistenceError;
import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.repository.AuthorRepository;

public class AuthorBookVerifierImpl implements AuthorBookVerifier {

    private final AuthorRepository authorRepository;

    public AuthorBookVerifierImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void verificar(Book book) throws ClienteConsistenceError {
        if (book == null) {
            throw new ClienteConsistenceError("Livro não pode ser nulo.");
        }

        if (book.getAuthorId() == null) {
            throw new ClienteConsistenceError("O livro precisa ter um autor associado.");
        }

        boolean autorExiste = authorRepository.findById(book.getAuthorId()).isPresent();

        if (!autorExiste) {
            throw new ClienteConsistenceError("O autor informado não existe no banco de dados.");
        }
    }
}
