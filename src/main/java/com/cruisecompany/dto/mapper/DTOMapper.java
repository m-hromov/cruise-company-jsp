package com.cruisecompany.dto.mapper;

import com.cruisecompany.dto.CruiseShowDTO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.Ticket;

public final class DTOMapper {
    public static CruiseShowDTO toCruiseShowDTO(Cruise cruise) {
        return CruiseShowDTO.builder()
                .id(cruise.getId())
                .description(cruise.getDescription())
                .shipName(cruise.getShip().getName())
                .photoPath(cruise.getShip().getPhotoPath())
                .price(cruise.getPrice())
                .timeDeparture(cruise.getTimeDeparture())
                .daysTotal(cruise.getDaysTotal())
                .ticketsPurchased(cruise.getTicketsPurchased())
                .shipCapacity(cruise.getShip().getPassengerCapacity())
                .dateArrival(cruise.getDateArrival())
                .dateDeparture(cruise.getDateDeparture())
                .start(cruise.getStationList().get(0))
                .end(cruise.getStationList().get(cruise.getStationList().size() - 1))
                .build();
    }

    public static PassengerOrderDTO toPassengerOrderDTO(Ticket ticket) {
        Passenger passenger = ticket.getPassenger();
        Cruise cruise = ticket.getCruise();
        return PassengerOrderDTO.builder()
                .passengerId(passenger.getId())
                .orderId(ticket.getId())
                .cruiseId(cruise.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .phone(passenger.getPhone())
                .email(passenger.getUserAccount().getEmail())
                .paid(ticket.isPaid())
                .banned(ticket.isBanned())
                .confirmed(ticket.isConfirmed())
                .documentPath(passenger.getDocumentPath())
                .build();
    }

    public static PassengerDTO toPassengerDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .passengerId(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .phone(passenger.getPhone())
                .email(passenger.getUserAccount().getEmail())
                .userAccountId(passenger.getUserAccount().getId())
                .money(passenger.getMoney())
                .role(passenger.getUserAccount().getRole())
                .documentPath(passenger.getDocumentPath())
                .build();
    }

}
