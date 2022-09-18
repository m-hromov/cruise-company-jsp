package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.impl.CruiseDAOImpl;
import com.cruisecompany.db.dao.impl.StationDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Route;
import com.cruisecompany.db.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.dao.mapper.Tables.CRUISE;
import static com.cruisecompany.db.dao.mapper.Tables.STATION;
import static com.cruisecompany.db.dao.mapper.impl.Columns.*;

public class RouteRowMapper implements RowMapper<Route> {
    @Override
    public Route map(ResultSet rs) {
        try {
            RowMapper<Cruise> cruiseRowMapper = RowMapperFactory.getInstance().getCruiseRowMapper();
            CruiseDAO cruiseDAO = new CruiseDAOImpl(cruiseRowMapper, CRUISE);
            Cruise cruise = cruiseDAO.get(rs.getLong(ID_SHIP)).orElse(new Cruise());

            RowMapper<Station> stationRowMapper = RowMapperFactory.getInstance().getStationRowMapper();
            StationDAO stationDAO = new StationDAOImpl(stationRowMapper, STATION);
            Station station = stationDAO.get(rs.getLong(ID_STATION)).orElse(new Station());

            Route route = new Route();
            route.setId(rs.getLong(ID))
                    .setCruise(cruise)
                    .setStation(station)
                    .setOrderNumber(rs.getInt(ORDER_NUMBER));
            return route;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
