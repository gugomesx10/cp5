package br.com.fiap.domain.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;
import java.util.List;

public interface AuthorService {

    Author criar(Author author);
    Author buscarPorId(Long id) throws EntidadeNaoLocalizada;
    List<Author> listarTodos();

    Author buscarPorID(int id) throws EntidadeNaoLocalizada;

    Author atualizar(Author author);
    void deletar(Long id) throws EntidadeNaoLocalizada;

    void deletar(int id) throws EntidadeNaoLocalizada;
}
