package br.com.fiap.application.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.domain.service.BookService;
import br.com.fiap.domain.verifier.AuthorBookVerifier;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private BookRepository repository;
    private AuthorBookVerifier authorBookVerifier;

    public BookServiceImpl(BookRepository repository, AuthorBookVerifier authorBookVerifier) {
        this.repository = repository;
        this.authorBookVerifier = authorBookVerifier;
    }

    public BookServiceImpl(BookRepository bookRepository) {
    }

    @Override
    public Book criar(Book book) {
        authorBookVerifier.verificar(book);
        repository.save(book);
        return book;
    }

    @Override
    public Book buscarPorId(Long id) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public List<Book> listarTodos() {
        return List.of();
    }

    @Override
    public Book buscarPorID(int id) throws EntidadeNaoLocalizada {
        Optional<Book> book = repository.findById((long) id);
        return book.orElseThrow(() ->
                new EntidadeNaoLocalizada("Livro com ID " + id + " não encontrado"));
    }

    @Override
    public Book atualizar(Book book) {
        Optional<Book> existente = repository.findById(book.getId());
        if (existente.isEmpty()) {
            throw new EntidadeNaoLocalizada("Livro com ID " + book.getId() + " não encontrado");
        }
        authorBookVerifier.verificar(book);
        repository.delete(book.getId());
        repository.save(book);
        return book;
    }

    @Override
    public void deletar(Long id) throws EntidadeNaoLocalizada {

    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        Optional<Book> existente = repository.findById((long) id);
        if (existente.isEmpty()) {
            throw new EntidadeNaoLocalizada("Livro com ID " + id + " não encontrado");
        }
        repository.delete((long) id);
    }

    public List<Book> listarPorAutor(Long authorId) {
        return repository.findByAuthorId(authorId);
    }
}
