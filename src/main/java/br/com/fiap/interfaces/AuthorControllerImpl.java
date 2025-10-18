package br.com.fiap.interfaces;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.domain.service.AuthorService;

public class AuthorControllerImpl implements  AuthorController{

    private final AuthorService authorService;

    public AuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Override
    public Author criar(Author authorImput) {
        return this.authorService.criar(authorImput);
    }

    @Override
    public Author buscarPorID(int id) throws EntidadeNaoLocalizada {
        return this.authorService.buscarPorID(id);
    }

    @Override
    public Author atualizar(Author authorImput) throws EntidadeNaoLocalizada {
        return this.authorService.atualizar(authorImput);
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        this.authorService.deletar(id);
    }
}
