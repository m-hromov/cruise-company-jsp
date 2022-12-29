package com.cruisecompany.dao.mapper;

import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    /**
     * Maps a row from a ResultSet.
     * @param connection Connection on which some additional queries could be executed
     * @param rs ResultSet
     * @return an Object
     * @throws DAOException if additional querying went wrong
     * @throws SQLException if something else went wrong
     */
    T map(Connection connection, ResultSet rs) throws DAOException, SQLException;
}
