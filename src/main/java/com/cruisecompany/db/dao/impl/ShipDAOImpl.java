package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.ShipDAO;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class ShipDAOImpl extends AbstractDAO<Ship> implements ShipDAO {
    public ShipDAOImpl(RowMapper<Ship> rowMapper, String table) {
        super(rowMapper, table);
    }
    private static final String INSERT = "INSERT INTO "+ Tables.SHIP + " (" + Columns.PASSENGER_CAPACITY + ", " +
            Columns.SHIP_NAME + "," + Columns.PHOTO_PATH+ ") VALUES (?, ?, ?)";
    @Override
    public long save(Ship ship) {
        return executeInsert(INSERT,ship.getPassengerCapacity(),
                ship.getName(), ship.getPhotoPath());
    }

    @Override
    public void update(Ship obj) {

    }
}
