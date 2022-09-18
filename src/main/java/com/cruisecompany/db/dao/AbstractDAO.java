package com.cruisecompany.db.dao;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Identifiable> extends SimpleQueryExecutor<T> implements DAO<T> {
    private final String DELETE = "DELETE FROM %s WHERE id = ?";
    private final String GET = "SELECT * FROM %s WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM %s";
    private final String table;

    public AbstractDAO(RowMapper<T> rowMapper, String table) {
        super(rowMapper);
        this.table = table;
    }

    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable(executeQuery(String.format(GET, table), id).get(0));
    }

    @Override
    public List<T> getAll() {
        return executeQuery(String.format(GET_ALL, table));
    }

    @Override
    public void delete(long id) {
        executeUpdate(String.format(DELETE, table), id);
    }
}
