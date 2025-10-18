package br.com.fiap.domain.repository;

import br.com.fiap.domain.model.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    void save(Author author);
    List<Author> findAll();
    Optional<Author> findById(Long id);
    void update(Author author);
    void delete(Long id);
}
