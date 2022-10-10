package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.Columns.*;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet rs) throws DAOException, SQLException {
        Passenger passenger = RowMapperFactory.getInstance().getPassengerRowMapper().map(rs);

        Cruise cruise = RowMapperFactory.getInstance().getCruiseRowMapper().map(rs);

        Order order = new Order();
        order.setId(rs.getLong(ORDER_ID))
                .setCruise(cruise)
                .setPaid(rs.getBoolean(PAID))
                .setBanned(rs.getBoolean(BANNED))
                .setConfirmed(rs.getBoolean(Columns.CONFIRMED))
                .setPassenger(passenger);
        return order;
    }
}
