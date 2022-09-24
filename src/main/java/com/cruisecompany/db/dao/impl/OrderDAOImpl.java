package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.entity.Order;
import com.cruisecompany.db.dao.mapper.RowMapper;

import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {
    public OrderDAOImpl(RowMapper<Order> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(Order obj) {
        return 0;
    }

    @Override
    public void update(Order obj) {

    }

    private static final String GET_ALL_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id";
    @Override
    public List<Order> getAll() {
        return executeQuery(GET_ALL_ORDERS);
    }
    private static final String GET_ALL_PASSENGER_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "WHERE p.passenger_id = ?";
    @Override
    public List<Order> getAllPassengerOrders(long id) {
        return executeQuery(GET_ALL_PASSENGER_ORDERS,id);
    }
}
