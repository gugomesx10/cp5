package br.com.fiap.interfaces.mappers;

import br.com.fiap.domain.model.Author;
import br.com.fiap.interfaces.dto.input.AuthorInputDto;
import br.com.fiap.interfaces.dto.output.AuthorOutputDto;

public class AuthorMapper {

    private AuthorMapper() {}

    public static Author toEntity(AuthorInputDto dto) {
        return new Author(null, dto.getName(), dto.getEmail());
    }

    public static AuthorOutputDto toDto(Author author, int totalBooks) {
        AuthorOutputDto dto = new AuthorOutputDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setTotalBooks(totalBooks);
        return dto;
    }
}
