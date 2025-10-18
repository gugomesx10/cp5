package br.com.fiap.infrastructure.persistence;

import br.com.fiap.domain.model.Author;
import br.com.fiap.domain.repository.AuthorRepository;
import br.com.fiap.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcAuthorRepository implements AuthorRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcAuthorRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save(Author author) {
        String sql = "INSERT INTO AUTHOR (NAME, EMAIL) VALUES (?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, author.getName());
            stmt.setString(2, author.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar o autor", e);
        }
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT ID, NAME, EMAIL FROM AUTHOR";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                authors.add(new Author(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL")
                ));
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao listar autores", e);
        }
        return authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT ID, NAME, EMAIL FROM AUTHOR WHERE ID = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Author(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL")
                ));
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar autor por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM AUTHOR WHERE ID = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao deletar autor", e);
        }
    }
}
