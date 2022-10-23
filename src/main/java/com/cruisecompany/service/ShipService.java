package com.cruisecompany.service;

import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface ShipService {
    /**
     * Gets all ships.
     *
     * @return list of staff or empty list if no objects were found.
     * @throws ServiceException if something went wrong
     */
    List<Ship> getAll() throws ServiceException;

    /**
     * Adds a new ship.
     *
     * @param requestRealPath Passenger with a new document path.
     * @param photoPart       request part with photo
     * @param ship            ship to be inserted
     * @throws ServiceException if something went wrong
     */
    void addShip(Ship ship, Part photoPart, String requestRealPath) throws ServiceException;
}
