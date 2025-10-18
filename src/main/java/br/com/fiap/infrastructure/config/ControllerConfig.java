package br.com.fiap.infrastructure.config;

import br.com.fiap.domain.service.AuthorService;
import br.com.fiap.interfaces.AuthorController;
import br.com.fiap.interfaces.AuthorControllerImpl;
import br.com.fiap.domain.service.BookService;
import br.com.fiap.interfaces.BookController;
import br.com.fiap.interfaces.BookControllerImpl;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ControllerConfig {

    @ApplicationScoped
    public AuthorController authorController(AuthorService authorService) {
        return new AuthorControllerImpl(authorService);
    }

    @ApplicationScoped
    public BookController bookController(BookService bookService) {
        return new BookControllerImpl(bookService);
    }

}
