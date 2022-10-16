package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.Tables;
import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {
    private static final String GET_ALL_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "JOIN " + Tables.USER_ACCOUNT + " AS ua ON p." + Columns.USER_ACCOUNT_ID + " = ua." + Columns.USER_ACCOUNT_ID +
            " ORDER BY " + Columns.ORDER_ID + " DESC";
    private static final String GET_ALL_PASSENGER_ORDERS = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "JOIN " + Tables.USER_ACCOUNT + " AS ua ON p." + Columns.USER_ACCOUNT_ID + " = ua." + Columns.USER_ACCOUNT_ID +
            " WHERE p.passenger_id = ? ORDER BY " + Columns.ORDER_ID + " DESC";
    private static final String PAY = "UPDATE " + Tables.ORDER + " SET " + Columns.PAID + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";
    private static final String GET = "SELECT * FROM " + Tables.ORDER + " AS ord " +
            "JOIN " + Tables.CRUISE + " AS c ON c.cruise_id = ord.cruise_id " +
            "JOIN " + Tables.SHIP + " AS s ON s.ship_id = c.ship_id " +
            "JOIN " + Tables.PASSENGER + " AS p ON p.passenger_id = ord.passenger_id " +
            "JOIN " + Tables.USER_ACCOUNT + " AS ua ON p." + Columns.USER_ACCOUNT_ID + " = ua." + Columns.USER_ACCOUNT_ID +
            " WHERE ord.order_id = ?";
    private static final String INSERT = "INSERT INTO " + Tables.ORDER + " (" + Columns.PASSENGER_ID + ", " +
            Columns.CRUISE_ID + ") VALUES (?, ?)";
    private static final String BLOCK = "UPDATE " + Tables.ORDER + " SET " + Columns.BANNED + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";
    private static final String UNBLOCK = "UPDATE " + Tables.ORDER + " SET " + Columns.BANNED + " = FALSE WHERE " +
            Columns.ORDER_ID + " = ?";
    private static final String CONFIRMED = "UPDATE " + Tables.ORDER + " SET " + Columns.CONFIRMED + " = TRUE WHERE " +
            Columns.ORDER_ID + " = ?";

    public OrderDAOImpl(RowMapper<Order> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public Optional<Order> get(Connection connection, long id) throws DAOException {
        return executeSingleGetQuery(connection, GET, id);
    }

    @Override
    public long save(Connection connection, Order order) throws DAOException {
        return executeInsert(connection, INSERT, order.getPassenger().getId(), order.getCruise().getId());
    }

    @Override
    public void update(Connection connection, Order order) throws DAOException {

    }

    @Override
    public List<Order> getAll(Connection connection) throws DAOException {
        return executeQuery(connection, GET_ALL_ORDERS);
    }

    @Override
    public List<Order> getAllPassengerOrders(Connection connection, long id) throws DAOException {
        return executeQuery(connection, GET_ALL_PASSENGER_ORDERS, id);
    }


    @Override
    public void updatePaidStatus(Connection connection, long orderId) throws DAOException {
        executeUpdate(connection, PAY, orderId);
    }

    @Override
    public void block(Connection connection, long orderId) throws DAOException {
        executeUpdate(connection, BLOCK, orderId);
    }

    @Override
    public void unblock(Connection connection, long orderId) throws DAOException {
        executeUpdate(connection, UNBLOCK, orderId);
    }

    @Override
    public void confirm(Connection connection, long orderId) throws DAOException {
        executeUpdate(connection, CONFIRMED, orderId);
    }
}
