package br.com.fiap.domain.repository;

import br.com.fiap.domain.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    List<Book> findByAuthor(Long authorId);
    void update(Book book);
    void delete(Long id);

    List<Book> findByAuthorId(Long authorId);
}
