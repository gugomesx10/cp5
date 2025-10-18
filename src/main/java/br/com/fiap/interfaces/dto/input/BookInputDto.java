package br.com.fiap.interfaces.dto.input;

public class BookInputDto {

    private String title;
    private String isbn;
    private Long authorId;

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
