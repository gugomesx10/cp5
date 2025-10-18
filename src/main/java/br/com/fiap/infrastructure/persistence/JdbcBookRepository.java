package br.com.fiap.infrastructure.persistence;

import br.com.fiap.infrastructure.exceptions.InfraestruturaException;
import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Book;
import br.com.fiap.domain.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookRepository implements BookRepository {
    private final DatabaseConnection databaseConnection;

    public JdbcBookRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Book criar(Book book) {
        final String sql = """
                INSERT INTO T_BOOK (id_book, title, isbn, T_AUTHOR_id_author)
                VALUES (?, ?, ?, ?)
                """;
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getId_book());                 // ajuste para getIdBook() se necessário
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getAuthorId());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new InfraestruturaException("Error ao inserir livro.");
            }
            return book;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao inserir livro: " + e.getMessage(), e);
        }
    }

    @Override
    public Book buscarPorID(int id) throws EntidadeNaoLocalizada {
        final String sql = """
                SELECT id_book, title, isbn, T_AUTHOR_id_author
                  FROM T_BOOK
                 WHERE id_book = ?
                """;
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearBook(rs);
                }
            }
            throw new EntidadeNaoLocalizada("Livro não localizado (id=" + id + ").");

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar livro por id: " + e.getMessage(), e);
        }
    }

    private Book mapearBook(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id_author");
        String title = rs.getString("title");
        String isbn = rs.getString("isbn");
        Integer authorId = rs.getInt("T_AUTHOR_id_author");
        Book book = new Book(id, title, isbn, authorId);
        return book;
    }

    @Override
    public Book atualizar(Book book) {
        String sql = """
               UPDATE T_BOOK SET title = ?, isbn  = ?, T_AUTHOR_id_author = ? WHERE id_book = ?
               """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setInt(3, book.getAuthorId());
            stmt.setInt(4, book.getId_book());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new InfraestruturaException("Falha ao atualizar livro (id=" + book.getId_book() + ").");
            }
            return book;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao atualizar livro: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        final String sql = """
                DELETE FROM T_BOOK
                 WHERE id_book = ?
                """;
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new EntidadeNaoLocalizada("Livro não encontrado para deletar (id=" + id + ").");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao deletar livro: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Book> buscarBooksPorAuthor(int idAuthor) {
        final String sql = """
                SELECT id_book, title, isbn, T_AUTHOR_id_author
                  FROM T_BOOK
                 WHERE T_AUTHOR_id_author = ?
                 ORDER BY title
                """;
        final List<Book> books = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAuthor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapearBook(rs));
                }
            }
            return books;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao testar os livros por autor: " + e.getMessage(), e);
        }

    }
}
