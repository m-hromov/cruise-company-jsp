package com.cruisecompany.dao;

import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SimpleQueryExecutorTest {
    static Connection connection = null;
    static SimpleQueryExecutor<Station> simpleQueryExecutor;

    @BeforeAll
    static void setUp() {
        try {
            Properties properties = new Properties();
            properties.load(SimpleQueryExecutorTest.class.getClassLoader().getResourceAsStream("app.properties"));
            String username = properties.getProperty("database.username");
            String password = properties.getProperty("database.password");
            String url = properties.getProperty("database.url");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException | IOException ex) {
            fail();
        }
        RowMapper<Station> rowMapper = RowMapperFactory.getInstance().getStationRowMapper();
        simpleQueryExecutor = new SimpleQueryExecutor<>(rowMapper);
    }

    @AfterAll
    static void tearDown() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            fail();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                fail();
            }
        }
    }

    @Test
    void testExecuteQuery() throws DAOException {
        String GET_ALL = "SELECT * FROM " + Tables.STATION;
        List<Station> stationList = simpleQueryExecutor.executeQuery(connection, GET_ALL);
        assertNotNull(stationList);
    }

    @Test
    void executeSingleGetQuery() throws DAOException {
        String GET = "SELECT * FROM " + Tables.STATION + " WHERE " + Columns.STATION_ID + " = ?";
        Optional<Station> station = simpleQueryExecutor.executeSingleGetQuery(connection, GET, executeInsert());
        assertTrue(station.isPresent());
        assertEquals("Odesa", station.get().getCity());
        assertEquals("Ukraine", station.get().getCountry());
    }

    @Test
    void testExecuteInsert() throws DAOException {
        assertTrue(executeInsert() > 0);
    }

    long executeInsert() throws DAOException {
        String INSERT = "INSERT INTO " + Tables.STATION + " (" + Columns.STATION_CITY +
                ", " + Columns.STATION_COUNTRY + ") VALUES (?, ?)";
        return simpleQueryExecutor.executeInsert(connection, INSERT, "Odesa", "Ukraine");
    }

    @Test
    void testExecuteUpdate() throws DAOException {
        long id = executeInsert();
        String UPDATE = "UPDATE " + Tables.STATION + " SET " + Columns.STATION_CITY + "='Kyiv' WHERE " + Columns.STATION_ID + " = ?";
        simpleQueryExecutor.executeUpdate(connection, UPDATE, id);
        String GET = "SELECT * FROM " + Tables.STATION + " WHERE " + Columns.STATION_ID + " = ?";
        Optional<Station> station = simpleQueryExecutor.executeSingleGetQuery(connection, GET, id);
        assertTrue(station.isPresent());
        assertEquals("Kyiv", station.get().getCity());
        assertEquals("Ukraine", station.get().getCountry());
    }

    @Test
    void testExecuteSingleGetLongQuery() throws DAOException {
        String GET = "SELECT count(*) FROM " + Tables.USER_ACCOUNT;
        long value = simpleQueryExecutor.executeSingleGetLongQuery(connection, GET);
        assertTrue(value >= 0);
    }
}