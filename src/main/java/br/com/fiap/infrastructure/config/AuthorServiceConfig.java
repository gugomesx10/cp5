package br.com.fiap.infrastructure.config;

import br.com.fiap.application.service.AuthorServiceImpl;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.domain.service.AuthorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@ApplicationScoped
public class AuthorServiceConfig {
    private final AuthorRepository authorRepository;

    public AuthorServiceConfig(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestScoped
    public AuthorService authorService() {
        return new AuthorServiceImpl(authorRepository);
    }
}
