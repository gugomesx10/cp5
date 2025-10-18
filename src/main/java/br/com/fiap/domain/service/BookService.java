package br.com.fiap.domain.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;

import java.util.List;

public interface BookService {
    Book criar (Book book);
    Book buscarPorID (int id) throws EntidadeNaoLocalizada;
    Book atualizar (Book book) throws EntidadeNaoLocalizada;
    void deletar (int id) throws EntidadeNaoLocalizada;
    List<Book> buscarBooksPorAuthor (int idAuthor);
}
