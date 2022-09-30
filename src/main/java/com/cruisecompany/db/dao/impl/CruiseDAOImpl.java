package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Cruise;

import java.util.List;

public class CruiseDAOImpl extends AbstractDAO<Cruise> implements CruiseDAO {
    private static final String GET_ALL_CRUISE_STATIONS = "SELECT * FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id ";

    public CruiseDAOImpl(RowMapper<Cruise> rowMapper) {
        super(rowMapper, Tables.CRUISE);
    }
    public static final String INSERT = "INSERT INTO " + Tables.CRUISE + " (" + Columns.TIME_DEPARTURE +
            "," + Columns.DATE_DEPARTURE + "," + Columns.DATE_ARRIVAL + "," + Columns.PRICE +
            "," + Columns.CRUISE_DESCRIPTION + "," + Columns.SHIP_ID + ") " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    @Override
    public long save(Cruise cruise) {
        return executeInsert(INSERT,cruise.getTimeDeparture(), cruise.getDateDeparture(),
                cruise.getDateArrival(), cruise.getPrice(), cruise.getDescription(), cruise.getShip().getId());
    }

    @Override
    public void update(Cruise obj) {

    }

    @Override
    public List<Cruise> getAll() {
        return executeQuery(GET_ALL_CRUISE_STATIONS);
    }
}
