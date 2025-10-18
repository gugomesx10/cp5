package br.com.fiap.interfaces;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.service.BookService;

import java.util.List;

public class BookControllerImpl implements BookController {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book criar(Book book) {
        return bookService.criar(book);
    }

    @Override
    public Book buscarPorID(int id) throws EntidadeNaoLocalizada {
        return bookService.buscarPorID(id);
    }

    @Override
    public Book atualizar(Book book) throws EntidadeNaoLocalizada {
        return bookService.atualizar(book);
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        this.bookService.deletar(id);
    }

    @Override
    public List<Book> buscarBooksPorAuthor(int idAuthor) {
        return this.bookService.buscarBooksPorAuthor(idAuthor);
    }
}
