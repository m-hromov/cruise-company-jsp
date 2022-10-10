package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.exception.DAOException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CruiseDAOImpl extends AbstractDAO<Cruise> implements CruiseDAO {
    public static final String INSERT = "INSERT INTO " + Tables.CRUISE + " (" + Columns.TIME_DEPARTURE +
            "," + Columns.DATE_DEPARTURE + "," + Columns.DATE_ARRIVAL + "," + Columns.PRICE +
            "," + Columns.CRUISE_DESCRIPTION + "," + Columns.SHIP_ID + ") " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_CRUISE_STATIONS = "SELECT * FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id ";
    private static final String GET = "SELECT * FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "WHERE " + Columns.CRUISE_ID + "= ?";
    private static final String GET_ALL_FILTERED = "SELECT * FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "WHERE c." + Columns.DATE_DEPARTURE + " BETWEEN ? AND ? " +
            "AND c." + Columns.DATE_ARRIVAL + " BETWEEN ? AND ? " +
            "AND c." + Columns.DAYS_TOTAL + " BETWEEN ? AND ? " +
            "ORDER BY " + Columns.CRUISE_ID + " DESC " +
            "LIMIT ? OFFSET ?";
    private static final String GET_ROW_AMOUNT = "SELECT count(" + Columns.CRUISE_ID + ") FROM " + Tables.CRUISE + " AS c " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "WHERE c." + Columns.DATE_DEPARTURE + " BETWEEN ? AND ? " +
            "AND c." + Columns.DATE_ARRIVAL + " BETWEEN ? AND ? " +
            "AND c." + Columns.DAYS_TOTAL + " BETWEEN ? AND ? ";

    public CruiseDAOImpl(RowMapper<Cruise> rowMapper) {
        super(rowMapper, Tables.CRUISE);
    }

    @Override
    public List<Cruise> getAllFiltered(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                       int limit, int offset) throws DAOException {
        return executeQuery(GET_ALL_FILTERED, dateFrom, dateTo, dateFrom, dateTo, durationFrom, durationTo,
                limit, offset);
    }

    @Override
    public long getCruiseRowAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo) throws DAOException {
        return executeSingleGetLongQuery(GET_ROW_AMOUNT, dateFrom, dateTo, dateFrom, dateTo, durationFrom, durationTo);
    }

    @Override
    public long save(Cruise cruise) throws DAOException {
        return executeInsert(INSERT, cruise.getTimeDeparture(), cruise.getDateDeparture(),
                cruise.getDateArrival(), cruise.getPrice(), cruise.getDescription(), cruise.getShip().getId());
    }

    @Override
    public void update(Cruise obj) throws DAOException {

    }

    @Override
    public List<Cruise> getAll() throws DAOException {
        return executeQuery(GET_ALL_CRUISE_STATIONS);
    }

    @Override
    public Optional<Cruise> get(long id) throws DAOException {
        return executeSingleGetQuery(GET, id);
    }
}
