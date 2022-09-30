package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Order;

import java.util.List;
import java.util.Optional;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {
    private static final String GET_ALL_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id";
    private static final String GET_ALL_PASSENGER_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "WHERE p.passenger_id = ?";
    private static final String PAY = "UPDATE " + Tables.ORDER + " SET " + Columns.PAID + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";

    public OrderDAOImpl(RowMapper<Order> rowMapper, String table) {
        super(rowMapper, table);
    }

    private static final String GET = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "WHERE ord.order_id = ?";
    @Override
    public Optional<Order> get(long id) {
        return executeSingleGetQuery(GET,id);
    }
    private static final String INSERT = "INSERT INTO "+ Tables.ORDER + " ("+Columns.PASSENGER_ID+", " +
            Columns.CRUISE_ID +") VALUES (?, ?)";
    @Override
    public long save(Order order) {
        return executeInsert(INSERT,order.getPassenger().getId(), order.getCruise().getId());
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public List<Order> getAll() {
        return executeQuery(GET_ALL_ORDERS);
    }

    @Override
    public List<Order> getAllPassengerOrders(long id) {
        return executeQuery(GET_ALL_PASSENGER_ORDERS, id);
    }

    @Override
    public void updatePaidStatus(long orderId) {
        executeUpdate(PAY, orderId);
    }
    private static final String BLOCK = "UPDATE " + Tables.ORDER + " SET " + Columns.BANNED + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";
    @Override
    public void block(long orderId) {
        executeUpdate(BLOCK,orderId);
    }
    private static final String UNBLOCK = "UPDATE " + Tables.ORDER + " SET " + Columns.BANNED + " = FALSE WHERE " +
            Columns.ORDER_ID + " = ?";
    @Override
    public void unblock(long orderId) {
        executeUpdate(UNBLOCK,orderId);
    }
    private static final String CONFIRMED = "UPDATE " + Tables.ORDER + " SET " + Columns.CONFIRMED + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";
    @Override
    public void confirm(long orderId) {
        executeUpdate(CONFIRMED,orderId);
    }
}
