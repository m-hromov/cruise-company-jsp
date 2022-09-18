package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.dao.CruiseDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dao.impl.CruiseDAOImpl;
import com.cruisecompany.db.dao.impl.PassengerDAOImpl;
import com.cruisecompany.db.dao.mapper.RowMapper;
import com.cruisecompany.db.dao.mapper.RowMapperFactory;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.db.entity.PassengerCruise;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cruisecompany.db.dao.mapper.Tables.CRUISE;
import static com.cruisecompany.db.dao.mapper.Tables.PASSENGER;
import static com.cruisecompany.db.dao.mapper.impl.Columns.*;

public class PassengerCruiseRowMapper implements RowMapper<PassengerCruise> {
    @Override
    public PassengerCruise map(ResultSet rs) {
        try {
            RowMapper<Passenger> passengerRowMapper = RowMapperFactory.getInstance().getPassengerRowMapper();
            PassengerDAO passengerDAO = new PassengerDAOImpl(passengerRowMapper, PASSENGER);
            Passenger passenger = passengerDAO.get(rs.getLong(ID_PASSENGER)).orElse(new Passenger());

            RowMapper<Cruise> cruiseRowMapper = RowMapperFactory.getInstance().getCruiseRowMapper();
            CruiseDAO cruiseDAO = new CruiseDAOImpl(cruiseRowMapper, CRUISE);
            Cruise cruise = cruiseDAO.get(rs.getLong(ID_CRUISE)).orElse(new Cruise());

            PassengerCruise passengerCruise = new PassengerCruise();
            passengerCruise.setId(rs.getLong(ID))
                    .setCruise(cruise)
                    .setPaid(rs.getBoolean(PAID))
                    .setPassenger(passenger);
            return passengerCruise;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
