package br.com.fiap.infrastructure.config;

import br.com.fiap.application.service.AuthorServiceImpl;
import br.com.fiap.application.service.BookServiceImpl;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.domain.service.AuthorService;
import br.com.fiap.domain.service.BookService;
import br.com.fiap.infrastructure.persistence.DatabaseConnection;
import br.com.fiap.infrastructure.persistence.JdbcAuthorRepository;
import br.com.fiap.infrastructure.persistence.JdbcBookRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServiceConfig {

    @Inject
    DatabaseConfig databaseConfig;

    private AuthorService authorService;
    private BookService bookService;

    @PostConstruct
    void init() {
        DatabaseConnection connection = databaseConfig.getDatabaseConnection();

        AuthorRepository authorRepository = new JdbcAuthorRepository(connection);
        BookRepository bookRepository = new JdbcBookRepository(connection);

        this.authorService = new AuthorServiceImpl(authorRepository);
        this.bookService = new BookServiceImpl(bookRepository);
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
