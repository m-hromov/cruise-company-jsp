package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.StationDAO;
import com.cruisecompany.db.dao.impl.PassengerDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.entity.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.cruisecompany.db.Tables.PASSENGER;
import static com.cruisecompany.db.Columns.*;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet rs) {
        try {
            RowMapper<Passenger> passengerRowMapper = RowMapperFactory.getInstance().getPassengerRowMapper();
            PassengerDAO passengerDAO = new PassengerDAOImpl(passengerRowMapper, PASSENGER);
            Passenger passenger = passengerDAO.get(rs.getLong(PASSENGER_ID)).orElse(new Passenger());

            Ship ship = RowMapperFactory.getInstance().getShipRowMapper().map(rs);

            StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
            List<Station> stationList = stationDAO.getAllStationsByCruiseID(rs.getLong(CRUISE_ID));
            
            Cruise cruise = new Cruise();
            cruise.setId(rs.getLong(CRUISE_ID))
                    .setTimeDeparture(LocalTime.parse(rs.getString(TIME_DEPARTURE)))
                    .setDateDeparture(LocalDate.parse(rs.getString(DATE_DEPARTURE)))
                    .setDateArrival(LocalDate.parse(rs.getString(DATE_ARRIVAL)))
                    .setDaysTotal(rs.getInt(DAYS_TOTAL))
                    .setDescription(rs.getString(CRUISE_DESCRIPTION))
                    .setPrice(BigDecimal.valueOf(rs.getDouble(PRICE)))
                    .setShip(ship)
                    .setStationList(stationList);

            Order order = new Order();
            order.setId(rs.getLong(ORDER_ID))
                    .setCruise(cruise)
                    .setPaid(rs.getBoolean(PAID))
                    .setBanned(rs.getBoolean(BANNED))
                    .setConfirmed(rs.getBoolean(Columns.CONFIRMED))
                    .setPassenger(passenger);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
