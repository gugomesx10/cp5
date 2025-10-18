package br.com.fiap.interfaces;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;

public interface AuthorController {

    Author criar(Author author);

    Author buscarPorID(int id) throws EntidadeNaoLocalizada;

    Author atualizar(Author author);

    void deletar(int id) throws EntidadeNaoLocalizada;
}
