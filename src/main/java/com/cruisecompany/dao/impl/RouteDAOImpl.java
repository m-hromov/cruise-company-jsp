package com.cruisecompany.dao.impl;

import com.cruisecompany.dao.SimpleQueryExecutor;
import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.RouteDAO;
import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.AbstractDAO;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.impl.RouteRowMapper;
import com.cruisecompany.entity.Route;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RouteDAOImpl extends SimpleQueryExecutor<Route> implements RouteDAO {
    private static final String INSERT = "INSERT INTO " + Tables.ROUTE + " (" + Columns.CRUISE_ID +
            ", " + Columns.STATION_ID + "," + Columns.ORDER_NUMBER + ") VALUES (?, ?, ?)";

    public RouteDAOImpl(RowMapper<Route> rowMapper) {
        super(rowMapper);
    }

    @Override
    public long save(Connection connection, Route route) throws DAOException {
        return executeInsert(connection, INSERT, route.getCruise().getId(),
                route.getStation().getId(), route.getOrderNumber());
    }

    @Override
    public void update(Connection connection, Route obj) throws DAOException {
        throw new RuntimeException();
    }

    @Override
    public Optional<Route> get(Connection connection, long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<Route> getAll(Connection connection) throws DAOException {
        throw new RuntimeException();
    }

    @Override
    public void delete(Connection connection, long id) throws DAOException {
        throw new RuntimeException();
    }
}
