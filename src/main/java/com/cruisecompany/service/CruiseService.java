package com.cruisecompany.service;

import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseService {
    /**
     * Gets the list of all cruises converted to CruiseShowDTO
     *
     * @return List of CruiseShowDTO
     * @throws ServiceException if something went wrong
     */
    List<CruiseShowDTO> getAllCruiseShowDTO() throws ServiceException;

    /**
     * Gets the list of all cruises converted to CruiseShowDTO that match the parameters.
     *
     * @param dateFrom     a start point of a date gap
     * @param dateTo       an end point of a date gap
     * @param durationFrom a start point of a duration gap
     * @param durationTo   an end point of a duration gap
     * @param limit        a max amount of cruises that will be returned
     * @param offset       an offset
     * @return List of Cruise that matches the parameters
     * @throws ServiceException if something went wrong
     */
    List<CruiseShowDTO> getAllFilteredCruiseShowDTO(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                                                    int limit, int offset) throws ServiceException;

    /**
     * Calculates the amount of pages with specified parameters.
     *
     * @param dateFrom     a start point of a date gap
     * @param dateTo       an end point of a date gap
     * @param durationFrom a start point of a duration gap
     * @param durationTo   an end point of a duration gap
     * @param limit        a max amount of rows that will be returned
     * @return the amount of pages
     * @throws ServiceException if something went wrong
     */
    int getPageAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit) throws ServiceException;

    /**
     * Gets the list of all cruises converted to CruiseShowDTO by specified search string.
     *
     * @param str the search string
     * @return List of Cruise that matches the search string
     */
    List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str);

    /**
     * Adds cruise to the database
     *
     * @param cruise Cruise to be added
     * @throws ServiceException if something went wrong
     */
    void addCruise(Cruise cruise) throws ServiceException;

    /**
     * Gets a CruiseShowDTO by specified cruise ID.
     *
     * @param id cruise ID
     * @return Nullable Optional of CruiseShowDTO
     * @throws ServiceException if something went wrong
     */
    Optional<CruiseShowDTO> getCruiseShowDTOById(long id) throws ServiceException;
}
