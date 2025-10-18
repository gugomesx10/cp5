package br.com.fiap.application.service;

import br.com.fiap.application.exception.BookUnsupportedOperation;
import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.domain.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book criar(Book book) {
        try {
            buscarPorID(book.getId_book());
            throw new BookUnsupportedOperation("Livro foi cadastrado");
        } catch (EntidadeNaoLocalizada e) {
            return bookRepository.criar(book);
        }
    }

    @Override
    public Book atualizar(Book book) throws EntidadeNaoLocalizada {
        try {
            buscarPorID(book.getId_book());
            return bookRepository.atualizar(book);
        } catch (EntidadeNaoLocalizada e) {
            throw new EntidadeNaoLocalizada("O livro que está tentando editar não existe ou não está disponivel");
        }
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        try {
            buscarPorID(id);
            bookRepository.deletar(id);
        } catch (EntidadeNaoLocalizada e) {
            throw new EntidadeNaoLocalizada("Livro não localizado");
        }
    }

    @Override
    public List<Book> buscarBooksPorAuthor(int idAuthor) {
        return bookRepository.buscarBooksPorAuthor(idAuthor);
    }

    @Override
    public Book buscarPorID(int id) throws EntidadeNaoLocalizada {
        return bookRepository.buscarPorID(id);
    }
}
