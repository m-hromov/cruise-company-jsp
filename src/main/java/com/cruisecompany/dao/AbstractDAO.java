package com.cruisecompany.dao;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.Identifiable;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Identifiable> extends SimpleQueryExecutor<T> implements DAO<T> {
    private final String DELETE = "DELETE FROM %s WHERE %s_id = ?";
    private final String GET = "SELECT * FROM %s WHERE %s_id = ?";
    private final String GET_ALL = "SELECT * FROM %s";
    private final String table;

    public AbstractDAO(RowMapper<T> rowMapper, String table) {
        super(rowMapper);
        this.table = table;
    }

    @Override
    public Optional<T> get(Connection connection, long id) throws DAOException {
        return executeSingleGetQuery(connection, String.format(GET, table, table),
                id);
    }

    @Override
    public List<T> getAll(Connection connection) throws DAOException {
        return executeQuery(connection, String.format(GET_ALL, table));
    }

    @Override
    public void delete(Connection connection, long id) throws DAOException {
        executeUpdate(connection, String.format(DELETE, table, table), id);
    }
}
