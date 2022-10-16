package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;

public class ShipDAOImpl extends AbstractDAO<Ship> implements ShipDAO {
    private static final String INSERT = "INSERT INTO " + Tables.SHIP + " (" + Columns.PASSENGER_CAPACITY + ", " +
            Columns.SHIP_NAME + "," + Columns.PHOTO_PATH + ") VALUES (?, ?, ?)";

    public ShipDAOImpl(RowMapper<Ship> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Connection connection, Ship ship) throws DAOException {
        return executeInsert(connection, INSERT, ship.getPassengerCapacity(),
                ship.getName(), ship.getPhotoPath());
    }

    @Override
    public void update(Connection connection, Ship obj) throws DAOException {

    }
}
