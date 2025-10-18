package br.com.fiap.domain.repository;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;

import java.util.List;

public interface BookRepository {
    Book criar (Book book);
    Book buscarPorID (int id) throws EntidadeNaoLocalizada;
    Book atualizar (Book book);
    void deletar (int id) throws EntidadeNaoLocalizada;
    List<Book> buscarBooksPorAuthor (int idAuthor);
}
