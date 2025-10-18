package br.com.fiap.interfaces;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;

public interface BookController {

    Book criar(Book book);

    Book buscarPorID(int id) throws EntidadeNaoLocalizada;

    Book atualizar(Book book);

    void deletar(int id) throws EntidadeNaoLocalizada;
}
