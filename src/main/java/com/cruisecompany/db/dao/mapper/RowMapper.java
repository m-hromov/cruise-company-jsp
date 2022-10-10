package com.cruisecompany.db.dao.mapper;

import com.cruisecompany.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T map(ResultSet rs) throws DAOException, SQLException;
}
