package br.com.fiap.domain.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;
import java.util.List;

public interface BookService {

    Book criar(Book book);
    Book buscarPorId(Long id) throws EntidadeNaoLocalizada;
    List<Book> listarTodos();

    void deletar(int id) throws EntidadeNaoLocalizada;

    List<Book> listarPorAutor(Long authorId);

    Book buscarPorID(int id) throws EntidadeNaoLocalizada;

    Book atualizar(Book book);
    void deletar(Long id) throws EntidadeNaoLocalizada;
}
