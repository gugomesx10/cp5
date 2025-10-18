package br.com.fiap.infrastructure.persistence;

import br.com.fiap.infrastructure.exceptions.InfraestruturaException;
import br.com.fiap.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.domain.model.Author;
import br.com.fiap.domain.repository.AuthorRepository;

import java.sql.*;

public class JdbcAuthorRepository implements AuthorRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcAuthorRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Author criar(Author author) {
        String sql = """
                INSERT INTO T_AUTHOR (id_author, nome, email) VALUES (?, ?, ?)
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, author.getId_author());
            stmt.setString(2, author.getNome());
            stmt.setString(3, author.getEmail());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Error ao criar autor, nenhuma linha que tenha sido prejudicada.");
            }

            return author;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar autor: " + e.getMessage(), e);
        }
    }

    @Override
    public Author buscarPorID(int id) throws EntidadeNaoLocalizada {
        String sql = """
                SELECT * FROM T_AUTHOR WHERE id_author = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Author authorencontrado = mapearAuthor(rs);
                    return authorencontrado;
                } else {
                    throw new EntidadeNaoLocalizada("Autor não encontrado para o id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar autor: " + e.getMessage(), e);
        }
    }

    private Author mapearAuthor(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id_author");
        String nome = rs.getString("nome");
        String email = rs.getString("email");

        return new Author(id, nome, email);
    }

    @Override
    public Author atualizar(Author author) throws EntidadeNaoLocalizada {
        String sql = """
                UPDATE T_AUTHOR SET nome = ?, email = ? WHERE id_author = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, author.getNome());
            stmt.setString(2, author.getEmail());
            stmt.setInt(3, author.getId_author());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Falha ao atualizar autor.");
            }

            return author;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar autor: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(int id) throws EntidadeNaoLocalizada {
        String sql = """
                DELETE FROM T_AUTHOR WHERE id_author = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Falha ao deletar autor.");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao deletar autor: " + e.getMessage(), e);
        }

    }
}
