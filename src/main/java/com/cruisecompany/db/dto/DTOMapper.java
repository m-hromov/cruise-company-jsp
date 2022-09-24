package com.cruisecompany.db.dto;

import com.cruisecompany.db.entity.Cruise;
import com.cruisecompany.db.entity.Order;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.db.entity.UserAccount;

import static com.cruisecompany.db.Columns.*;

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
    public static CruiseShowDTO toCruiseShowDTO(Order order) {
        Cruise cruise = order.getCruise();
        return toCruiseShowDTO(cruise);
    }

    public static PassengerOrderDTO toPassengerOrderDTO(Order order) {
        Passenger passenger = order.getPassenger();
        Cruise cruise = order.getCruise();
        PassengerOrderDTO passengerOrderDTO = new PassengerOrderDTO();

        passengerOrderDTO.setPassengerId(passenger.getId())
                .setOrderId(order.getId())
                .setCruiseId(cruise.getId())
                .setFirstName(passenger.getFirstName())
                .setLastName(passenger.getLastName())
                .setPhone(passenger.getPhone())
                .setEmail(passenger.getEmail())
                .setPaid(order.isPaid())
                .setBanned(order.isBanned())
                .setDocumentPath(passenger.getDocumentPath());
        return passengerOrderDTO;
    }
    public static UserAccountDTO toUserAccountDTO(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccount.getId())
                .setLogin(userAccount.getLogin())
                .setRole(userAccount.getRole());
        return userAccountDTO;
    }
}
