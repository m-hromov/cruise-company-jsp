package com.cruisecompany.db.dao;

import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;

public interface PassengerDAO extends DAO<Passenger> {
    /**
     * Gets passenger by user account ID.
     *
     * @param connection Connection, on which a query will be executed
     * @param id         ID of the passenger's UserAccount
     * @return Nullable Optional of Passenger
     * @throws DAOException if something went wrong
     */
    Optional<Passenger> getByUserAccountId(Connection connection, long id) throws DAOException;


    /**
     * Updates information about first/last name, email, phone.
     *
     * @param connection   Connection, on which a query will be executed
     * @param passengerDTO Passenger with a new first/last name or email, or phone.
     * @throws DAOException if something went wrong
     */
    void updateProfile(Connection connection, PassengerDTO passengerDTO) throws DAOException;

    /**
     * Updates passenger's document
     *
     * @param connection   Connection, on which a query will be executed
     * @param passengerDTO Passenger with a new document path.
     * @throws DAOException if something went wrong
     */
    void updateDocument(Connection connection, PassengerDTO passengerDTO) throws DAOException;

    /**
     * Subtracts passenger's amount of money.
     *
     * @param connection  Connection, on which a query will be executed
     * @param passengerId ID of the passenger
     * @param money       amount of money to be subtracted
     * @return BigDecimal - updated money
     * @throws DAOException if something went wrong
     */
    BigDecimal subtractMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException;

    /**
     * Adds money to passenger's account.
     *
     * @param connection  Connection, on which a query will be executed
     * @param passengerId ID of the passenger
     * @param money       an amount of money to be added
     * @return BigDecimal - updated money
     * @throws DAOException if something went wrong
     */
    BigDecimal addMoney(Connection connection, long passengerId, BigDecimal money) throws DAOException;
}
