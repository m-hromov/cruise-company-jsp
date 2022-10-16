package com.cruisecompany.db.dao;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleQueryExecutor<T> {
    final static Logger logger = LogManager.getLogger(SimpleQueryExecutor.class);
    private final RowMapper<T> rowMapper;

    protected SimpleQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(Connection connection, String query, Object... params) throws DAOException {
        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = preparedStatement(query, connection, params);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rowMapper.map(rs));
            }
            return list;
        } catch (SQLException e) {
            logger.error("Unable to execute a query!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected Optional<T> executeSingleGetQuery(Connection connection, String query, Object... params) throws DAOException {
        try (PreparedStatement ps = preparedStatement(query, connection, params);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next())
                return Optional.ofNullable(rowMapper.map(rs));
            return Optional.empty();
        } catch (SQLException e) {
            logger.error("Unable to execute a single get query!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected long executeInsert(Connection connection, String query, Object... params) throws DAOException {
        try (PreparedStatement ps = preparedStatement(query, connection, params)) {
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (!generatedKeys.next()) throw new SQLException("0 rows affected");
            return generatedKeys.getLong(1);

        } catch (SQLException e) {
            logger.error("Unable to execute an insert!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected void executeUpdate(Connection connection, String query, Object... params) throws DAOException {
        try (PreparedStatement ps = preparedStatement(query, connection, params)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to execute an update!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected long executeSingleGetLongQuery(Connection connection, String query, Object... params) throws DAOException {
        try (PreparedStatement ps = preparedStatement(query, connection, params)) {
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new SQLException("0 rows affected");
            return rs.getLong(1);

        } catch (SQLException e) {
            logger.error("Unable to execute a single grouped query with long type return!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    private PreparedStatement preparedStatement(String query, Connection connection, Object... params) throws DAOException {
        try {PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= params.length; i++) {
                ps.setObject(i, params[i - 1]);
            }
            return ps;
        } catch (SQLException e) {
            logger.error("Unable to prepare a statement!", e);
            throw new DAOException(e.getMessage(), e);
        }
    }
}
