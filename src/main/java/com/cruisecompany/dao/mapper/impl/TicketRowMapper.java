package com.cruisecompany.dao.mapper.impl;

import com.cruisecompany.dao.db.Columns;
import com.cruisecompany.dao.mapper.RowMapper;
import com.cruisecompany.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Ticket;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.dao.db.Columns.*;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket map(Connection connection, ResultSet rs) throws DAOException, SQLException {
        Passenger passenger = RowMapperFactory.getInstance().getPassengerRowMapper().map(connection, rs);

        Cruise cruise = RowMapperFactory.getInstance().getCruiseRowMapper().map(connection, rs);

        return Ticket.builder()
                .id(rs.getLong(TICKET_ID))
                .cruise(cruise)
                .paid(rs.getBoolean(PAID))
                .banned(rs.getBoolean(BANNED))
                .confirmed(rs.getBoolean(Columns.CONFIRMED))
                .passenger(passenger)
                .build();
    }
}
