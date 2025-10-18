package br.com.fiap.application.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.domain.service.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author criar(Author author) {
        repository.save(author);
        return author;
    }

    @Override
    public Author buscarPorId(Long id) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public List<Author> listarTodos() {
        return List.of();
    }

    @Override
    public Author buscarPorID(int id) throws EntidadeNaoLocalizada {
        Optional<Author> author = repository.findById((long) id);
        return author.orElseThrow(() ->
                new EntidadeNaoLocalizada("Autor com ID " + id + " não encontrado"));
    }

    @Override
    public Author atualizar(Author author) {
        Optional<Author> existente = repository.findById(author.getId());
        if (existente.isEmpty()) {
            throw new EntidadeNaoLocalizada("Autor com ID " + author.getId() + " não encontrado");
        }
        repository.delete(author.getId());
        repository.save(author);
        return author;
    }

    @Override
    public void deletar(Long id) throws EntidadeNaoLocalizada {

    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        Optional<Author> existente = repository.findById((long) id);
        if (existente.isEmpty()) {
            throw new EntidadeNaoLocalizada("Autor com ID " + id + " não encontrado");
        }
        repository.delete((long) id);
    }
}
