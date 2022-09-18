package com.cruisecompany.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBProvider {
    private static DBProvider INSTANCE;
    private DataSource dataSource;
    private DBProvider() {
        try {
            Context ctx = new InitialContext();
            Context initCtx  = (Context) ctx.lookup("java:/comp/env");
            dataSource = (DataSource) initCtx.lookup("jdbc/cruise_company");
        } catch (Exception e) {
            System.out.println("f");
        }
    }

    public static DBProvider getInstance() {
        if(INSTANCE == null) INSTANCE = new DBProvider();
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
