package br.com.fiap.domain.service;

import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;

public interface AuthorService {
    Author criar (Author author);
    Author buscarPorID (int id) throws EntidadeNaoLocalizada;
    Author atualizar (Author author) throws EntidadeNaoLocalizada;
    void deletar (int id) throws EntidadeNaoLocalizada;
}
