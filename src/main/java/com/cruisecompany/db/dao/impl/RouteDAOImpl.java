package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Route;
import com.cruisecompany.exception.DAOException;

public class RouteDAOImpl extends AbstractDAO<Route> implements RouteDAO {


    private static final String INSERT = "INSERT INTO " + Tables.ROUTE + " (" + Columns.CRUISE_ID +
            ", " + Columns.STATION_ID + "," + Columns.ORDER_NUMBER + ") VALUES (?, ?, ?)";

    public RouteDAOImpl(RowMapper<Route> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Route route) throws DAOException {
        return executeInsert(INSERT, route.getCruise().getId(),
                route.getStation().getId(), route.getOrderNumber());
    }

    @Override
    public void update(Route obj) throws DAOException {

    }


}
