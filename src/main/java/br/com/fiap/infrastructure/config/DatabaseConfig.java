package br.com.fiap.infrastructure.config;

import br.com.fiap.infrastructure.persistence.DatabaseConnection;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Configura a conexão com o banco Oracle usando o DataSource
 * que o Quarkus já fornece automaticamente a partir do application.properties.
 */
@ApplicationScoped
public class DatabaseConfig {

    private DatabaseConnection databaseConnection;

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }
}
