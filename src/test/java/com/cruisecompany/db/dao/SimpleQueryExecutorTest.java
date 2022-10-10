package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Station;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class SimpleQueryExecutorTest {
    static Connection connection;
    static SimpleQueryExecutor<Station> simpleQueryExecutor;
    @BeforeAll
    static void setUp() {
        try {
            Properties properties = new Properties();
            FileInputStream is = new FileInputStream("application.properties");
            properties.load(is);
            String username = properties.getProperty("database.username");
            String password = properties.getProperty("database.password");
            String url = properties.getProperty("database.url");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException | IOException ex) {
            fail();
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            fail();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                fail();
            }
        }
    }

    @Test
    void testExecuteQuery() {
    }

    @Test
    void executeSingleGetQuery() {
    }

    @Test
    void executeInsert() {
    }

    @Test
    void executeUpdate() {
    }

    @Test
    void executeSingleGetLongQuery() {
    }
}