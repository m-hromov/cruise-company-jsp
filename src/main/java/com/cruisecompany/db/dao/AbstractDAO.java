package com.cruisecompany.db.dao;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Identifiable;
import com.cruisecompany.exception.DAOException;

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
    public Optional<T> get(long id) throws DAOException {
        return Optional.ofNullable(executeQuery(String.format(GET, table, table.replace("\"","")), id).get(0));
    }

    @Override
    public List<T> getAll() throws DAOException{
        return executeQuery(String.format(GET_ALL, table));
    }

    @Override
    public void delete(long id) throws DAOException{
        executeUpdate(String.format(DELETE, table, table.replace("\"","")), id);
    }
}
