package com.cruisecompany.db.dto;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.RouteDAO;
import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Route;

import java.util.List;

public class DTOMapper {
    public static CruiseShowDTO toCruiseShowDTO(Cruise cruise) {
        CruiseShowDTO cruiseShowDTO = new CruiseShowDTO();
        cruiseShowDTO.setId(cruise.getId())
                .setDescription(cruise.getDescription())
                .setShipName(cruise.getShip().getName())
                .setPhotoPath(cruise.getShip().getPhotoPath())
                .setPrice(cruise.getPrice())
                .setTimeDeparture(cruise.getTimeDeparture())
                .setDaysTotal(cruise.getDaysTotal())
                .setDateArrival(cruise.getDateArrival())
                .setDateArrival(cruise.getDateArrival())
                .setStart(cruise.getStationList().get(0))
                .setEnd(cruise.getStationList().get(cruise.getStationList().size()-1));
        return cruiseShowDTO;
    }
}
