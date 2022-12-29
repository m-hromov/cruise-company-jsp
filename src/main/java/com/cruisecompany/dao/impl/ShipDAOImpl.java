package com.cruisecompany.dao.impl;

import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.db.Tables;
import com.cruisecompany.dao.AbstractDAO;
import com.cruisecompany.dao.ShipDAO;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;

public class ShipDAOImpl extends AbstractDAO<Ship> implements ShipDAO {
    private static final String INSERT = "INSERT INTO " + Tables.SHIP + " (" + Columns.PASSENGER_CAPACITY + ", " +
            Columns.SHIP_NAME + "," + Columns.PHOTO_PATH + ") VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE " + Tables.SHIP + " SET " + Columns.PASSENGER_CAPACITY + "=?, " +
             Columns.SHIP_NAME + "=?," + Columns.PHOTO_PATH + "=? WHERE " + Columns.SHIP_ID + "=?";

    public ShipDAOImpl(RowMapper<Ship> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Connection connection, Ship ship) throws DAOException {
        return executeInsert(connection, INSERT, ship.getPassengerCapacity(),
                ship.getName(), ship.getPhotoPath());
    }

    @Override
    public void update(Connection connection, Ship ship) throws DAOException {
        executeUpdate(connection,UPDATE,ship.getPassengerCapacity(), ship.getName(),
                ship.getPhotoPath(), ship.getId());
    }
}
