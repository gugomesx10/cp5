package br.com.fiap.interfaces.mappers;

import br.com.fiap.domain.model.Book;
import br.com.fiap.interfaces.dto.input.BookInputDto;
import br.com.fiap.interfaces.dto.output.BookOutputDto;

public class BookMapper {

    private BookMapper() {}

    public static Book toEntity(BookInputDto dto) {
        return new Book(null, dto.getTitle(), dto.getIsbn(), dto.getAuthorId());
    }

    public static BookOutputDto toDto(Book book, String authorName) {
        BookOutputDto dto = new BookOutputDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setAuthorName(authorName);
        return dto;
    }
}
