package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Route;
import com.cruisecompany.entity.Station;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.ORDER_NUMBER;

public class RouteRowMapper implements RowMapper<Route> {
    @Override
    public Route map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Cruise cruise = RowMapperFactory.getInstance().getCruiseRowMapper().map(connection, rs);

        Station station = RowMapperFactory.getInstance().getStationRowMapper().map(connection, rs);

        return Route.builder()
                .cruise(cruise)
                .station(station)
                .orderNumber(rs.getInt(ORDER_NUMBER))
                .build();
    }
}
