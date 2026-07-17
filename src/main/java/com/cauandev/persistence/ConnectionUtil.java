package com.cauandev.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {

    private static final Properties props = loadProperties();

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = ConnectionUtil.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if(input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado. Copie config.properties.example e preencha.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
        );
    }
}
