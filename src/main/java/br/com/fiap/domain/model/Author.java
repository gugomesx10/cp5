package br.com.fiap.domain.model;

import br.com.fiap.domain.exceptions.ValidacaoDominioException;

public class Author {

    private Long id;
    private String name;
    private String email;

    public Author(Long id, String name, String email) {
        validar(name, email);
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private void validar(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new ValidacaoDominioException("O nome do autor é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new ValidacaoDominioException("O email do autor é obrigatório.");
        }
        if (!email.contains("@")) {
            throw new ValidacaoDominioException("O email informado é inválido.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
