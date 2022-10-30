package com.cruisecompany.dto.mapper;

import com.cruisecompany.dto.CruiseShowDTO;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.PassengerOrderDTO;
import com.cruisecompany.dto.UserAccountDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;

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
                .setDateDeparture(cruise.getDateDeparture())
                .setStart(cruise.getStationList().get(0))
                .setEnd(cruise.getStationList().get(cruise.getStationList().size()-1));
        return cruiseShowDTO;
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
                .setConfirmed(order.isConfirmed())
                .setDocumentPath(passenger.getDocumentPath());
        return passengerOrderDTO;
    }

    public static PassengerDTO toPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setPassengerId(passenger.getId())
                .setFirstName(passenger.getFirstName())
                .setLastName(passenger.getLastName())
                .setPhone(passenger.getPhone())
                .setEmail(passenger.getEmail())
                .setUserAccountId(passenger.getUserAccount().getId())
                .setMoney(passenger.getMoney())
                .setRole(passenger.getUserAccount().getRole())
                .setDocumentPath(passenger.getDocumentPath());
        return passengerDTO;
    }
    public static UserAccountDTO toUserAccountDTO(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccount.getId())
                .setLogin(userAccount.getLogin())
                .setRole(userAccount.getRole());
        return userAccountDTO;
    }
}
