package br.com.fiap.infrastructure.config;

import br.com.fiap.application.service.AuthorServiceImpl;
import br.com.fiap.application.service.BookServiceImpl;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.domain.service.AuthorService;
import br.com.fiap.domain.service.BookService;
import br.com.fiap.domain.verifier.AuthorBookVerifier;
import br.com.fiap.domain.verifier.AuthorBookVerifierImpl;
import br.com.fiap.infrastructure.persistence.DatabaseConnection;
import br.com.fiap.infrastructure.persistence.DatabaseConnectionImpl;
import br.com.fiap.infrastructure.persistence.JdbcAuthorRepository;
import br.com.fiap.infrastructure.persistence.JdbcBookRepository;
import br.com.fiap.interfaces.AuthorController;
import br.com.fiap.interfaces.AuthorControllerImpl;
import br.com.fiap.interfaces.BookController;
import br.com.fiap.interfaces.BookControllerImpl;

import javax.sql.DataSource;

public class ControllerConfig {

    private final AuthorController authorController;
    private final BookController bookController;

    public ControllerConfig(DataSource dataSource) {

        // 🔹 Camada de Infraestrutura (JDBC)
        DatabaseConnection connection = new DatabaseConnectionImpl(dataSource);

        // 🔹 Repositórios
        AuthorRepository authorRepository = new JdbcAuthorRepository(connection);
        BookRepository bookRepository = new JdbcBookRepository(connection);

        // 🔹 Verificador de consistência entre Author e Book
        AuthorBookVerifier authorBookVerifier = new AuthorBookVerifierImpl(authorRepository);

        // 🔹 Serviços
        AuthorService authorService = new AuthorServiceImpl(authorRepository);
        BookService bookService = new BookServiceImpl(bookRepository, authorBookVerifier);

        // 🔹 Controllers
        this.authorController = new AuthorControllerImpl(authorService);
        this.bookController = new BookControllerImpl(bookService);
    }

    public AuthorController getAuthorController() {
        return authorController;
    }

    public BookController getBookController() {
        return bookController;
    }
}
