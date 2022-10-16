package com.cruisecompany.db;

import com.cruisecompany.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBProvider {
    final static Logger logger = LogManager.getLogger(DBProvider.class);
    private final DataSource dataSource;

    public DBProvider() throws DatabaseException {
        try {
            dataSource = InitialContext.doLookup("java:comp/env/jdbc/cruise_company");
        } catch (NamingException e) {
            logger.error("Unable to access a database!", e);
            throw new DatabaseException(e);
        }
    }
    public DBProvider(DataSource dataSource) throws DatabaseException {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws DatabaseException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Unable to get a connection!", e);
            throw new DatabaseException(e);
        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Unable to rollback a connection!", e);
            throw new DatabaseException(e);
        }
    }
    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Unable to close a connection!", e);
            throw new DatabaseException(e);
        }
    }
    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("Unable to commit a connection!", e);
            throw new DatabaseException(e);
        }
    }
}
