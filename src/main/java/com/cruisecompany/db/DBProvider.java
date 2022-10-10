package com.cruisecompany.db;

import com.cruisecompany.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBProvider {
    final static Logger logger = LogManager.getLogger(DBProvider.class);
    private static DBProvider INSTANCE;
    private final DataSource dataSource;

    private DBProvider() throws DatabaseException {
        try {
            Context ctx = new InitialContext();
            Context initCtx = (Context) ctx.lookup("java:/comp/env");
            dataSource = (DataSource) initCtx.lookup("jdbc/cruise_company");
        } catch (NamingException e) {
            logger.error("Unable to access a database!", e);
            throw new DatabaseException(e);
        }
    }

    public static DBProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBProvider();
        }
        return INSTANCE;
    }

    public Connection getConnection() throws DatabaseException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Unable to get a connection!", e);
            throw new DatabaseException(e);
        }
    }
}
