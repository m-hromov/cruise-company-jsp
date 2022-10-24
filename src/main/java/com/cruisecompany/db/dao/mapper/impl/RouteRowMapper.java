package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Route;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.ORDER_NUMBER;

public class RouteRowMapper implements RowMapper<Route> {
    @Override
    public Route map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Cruise cruise = RowMapperFactory.getInstance().getCruiseRowMapper().map(connection, rs);

        Station station = RowMapperFactory.getInstance().getStationRowMapper().map(connection, rs);

        Route route = new Route();
        route.setCruise(cruise)
                .setStation(station)
                .setOrderNumber(rs.getInt(ORDER_NUMBER));
        return route;
    }
}
