package br.com.fiap.infrastructure.config;

import br.com.fiap.application.service.BookServiceImpl;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.domain.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@ApplicationScoped
public class BookServiceConfig {
    private final BookRepository bookRepository;

    public BookServiceConfig(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestScoped
    public BookService bookService() {
        return new BookServiceImpl(bookRepository);
    }
}
