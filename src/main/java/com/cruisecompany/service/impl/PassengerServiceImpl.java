package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.entity.Order;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.PassengerService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PassengerServiceImpl implements PassengerService {
    private final PassengerDAO passengerDAO;
    private final OrderDAO orderDAO;

    public PassengerServiceImpl() {
        passengerDAO = DAOFactory.getInstance().getPassengerDAO();
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Override
    public List<PassengerOrderDTO> getAllPassengerOrderDTOList() {
        List<Order> orderList = orderDAO.getAll();
        return orderList.stream().map(DTOMapper::toPassengerOrderDTO).collect(Collectors.toList());
    }

    @Override
    public Passenger getPassengerByAccountId(long id) {
        Optional<Passenger> optional = passengerDAO.getByUserAccountId(id);
        return optional.orElseGet(Passenger::new);
    }

    @Override
    public void addMoney(long passengerId,BigDecimal money) {
        passengerDAO.addMoney(passengerId,money);
    }

    @Override
    public boolean updateProfile(Passenger passenger) {
        try {
            passengerDAO.updateProfile(passenger);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void updateDocument(Passenger passenger) {
        passengerDAO.updateDocument(passenger);
    }


}
