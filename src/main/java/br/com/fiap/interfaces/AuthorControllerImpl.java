package br.com.fiap.interfaces;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;
import br.com.fiap.domain.service.AuthorService;

public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    public AuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author criar(Author author) {
        return authorService.criar(author);
    }

    @Override
    public Author buscarPorID(int id) throws EntidadeNaoLocalizada {
        return authorService.buscarPorID(id);
    }

    @Override
    public Author atualizar(Author author) {
        return authorService.atualizar(author);
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        authorService.deletar(id);
    }
}
