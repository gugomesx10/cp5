package br.com.fiap.infrastructure.persistence;

import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.repository.BookRepository;
import br.com.fiap.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookRepository implements BookRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcBookRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO BOOK (TITLE, ISBN, AUTHOR_ID) VALUES (?, ?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setLong(3, book.getAuthorId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar o livro", e);
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT ID, TITLE, ISBN, AUTHOR_ID FROM BOOK";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("ID"),
                        rs.getString("TITLE"),
                        rs.getString("ISBN"),
                        rs.getLong("AUTHOR_ID")
                ));
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao listar livros", e);
        }
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "SELECT ID, TITLE, ISBN, AUTHOR_ID FROM BOOK WHERE ID = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Book(
                        rs.getLong("ID"),
                        rs.getString("TITLE"),
                        rs.getString("ISBN"),
                        rs.getLong("AUTHOR_ID")
                ));
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar livro por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByAuthor(Long authorId) {
        return List.of();
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM BOOK WHERE ID = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao deletar livro", e);
        }
    }

    public List<Book> findByAuthorId(Long authorId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT ID, TITLE, ISBN, AUTHOR_ID FROM BOOK WHERE AUTHOR_ID = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, authorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("ID"),
                        rs.getString("TITLE"),
                        rs.getString("ISBN"),
                        rs.getLong("AUTHOR_ID")
                ));
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao listar livros por autor", e);
        }
        return books;
    }
}
