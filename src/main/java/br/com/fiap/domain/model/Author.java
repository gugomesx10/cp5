package br.com.fiap.domain.model;

public class Author {
    private int id_author;
    private String nome;
    private String email;

    public Author(int id_author, String nome, String email) {
        this.id_author = id_author;
        this.nome = nome;
        this.email = email;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
