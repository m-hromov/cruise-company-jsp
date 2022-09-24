package com.cruisecompany.service.impl;

import com.cruisecompany.db.dao.DAOFactory;
import com.cruisecompany.db.dao.OrderDAO;
import com.cruisecompany.db.dao.PassengerDAO;
import com.cruisecompany.db.dto.DTOMapper;
import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.db.entity.Order;
import com.cruisecompany.db.entity.Passenger;
import com.cruisecompany.service.PassengerService;

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
}
