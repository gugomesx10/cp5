package br.com.fiap.domain.model;

public class Book {
    private int id_book;
    private String title;
    private String isbn;
    private int authorId;

    public Book(int id_book, String title, String isbn, int authorId) {
        this.id_book = id_book;
        this.title = title;
        this.isbn = isbn;
        this.authorId = authorId;
    }

    public int getId_book() {return id_book;}
    public void setId(int id) {this.id_book = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public int getAuthorId() {return authorId;}
    public void setAuthorId(int authorId) {this.authorId = authorId;}

}
