package br.com.fiap.domain.model;

import br.com.fiap.domain.exceptions.ValidacaoDominioException;

public class Book {

    private Long id;
    private String title;
    private String isbn;
    private Long authorId; // FK para AUTHOR

    public Book(String title, String isbn, Long authorId) {
        validar(title, isbn, authorId);
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
    }

    public Book(Long id, String title, String isbn, Long authorId) {
        validar(title, isbn, authorId);
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
    }

    private void validar(String title, String isbn, Long authorId) {
        if (title == null || title.isBlank()) {
            throw new ValidacaoDominioException("O título do livro é obrigatório.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new ValidacaoDominioException("O ISBN do livro é obrigatório.");
        }
        if (authorId == null) {
            throw new ValidacaoDominioException("O autor do livro é obrigatório.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
