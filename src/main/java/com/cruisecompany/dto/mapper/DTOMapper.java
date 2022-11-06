package com.cruisecompany.dto.mapper;

import com.cruisecompany.dto.CruiseShowDTO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.Ticket;

public class DTOMapper {
    public static CruiseShowDTO toCruiseShowDTO(Cruise cruise) {
        return new CruiseShowDTO()
                .setId(cruise.getId())
                .setDescription(cruise.getDescription())
                .setShipName(cruise.getShip().getName())
                .setPhotoPath(cruise.getShip().getPhotoPath())
                .setPrice(cruise.getPrice())
                .setTimeDeparture(cruise.getTimeDeparture())
                .setDaysTotal(cruise.getDaysTotal())
                .setTicketsPurchased(cruise.getTicketsPurchased())
                .setShipCapacity(cruise.getShip().getPassengerCapacity())
                .setDateArrival(cruise.getDateArrival())
                .setDateDeparture(cruise.getDateDeparture())
                .setStart(cruise.getStationList().get(0))
                .setEnd(cruise.getStationList().get(cruise.getStationList().size() - 1));
    }

    public static PassengerOrderDTO toPassengerOrderDTO(Ticket ticket) {
        Passenger passenger = ticket.getPassenger();
        Cruise cruise = ticket.getCruise();
        return new PassengerOrderDTO()
                .setPassengerId(passenger.getId())
                .setOrderId(ticket.getId())
                .setCruiseId(cruise.getId())
                .setFirstName(passenger.getFirstName())
                .setLastName(passenger.getLastName())
                .setPhone(passenger.getPhone())
                .setEmail(passenger.getUserAccount().getEmail())
                .setPaid(ticket.isPaid())
                .setBanned(ticket.isBanned())
                .setConfirmed(ticket.isConfirmed())
                .setDocumentPath(passenger.getDocumentPath());
    }

    public static PassengerDTO toPassengerDTO(Passenger passenger) {
        return new PassengerDTO()
                .setPassengerId(passenger.getId())
                .setFirstName(passenger.getFirstName())
                .setLastName(passenger.getLastName())
                .setPhone(passenger.getPhone())
                .setEmail(passenger.getUserAccount().getEmail())
                .setUserAccountId(passenger.getUserAccount().getId())
                .setMoney(passenger.getMoney())
                .setRole(passenger.getUserAccount().getRole())
                .setDocumentPath(passenger.getDocumentPath());
    }

}
