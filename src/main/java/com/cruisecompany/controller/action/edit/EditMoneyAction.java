package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class EditMoneyAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        PassengerService passengerService = ServiceFactory.getInstance().getPassengerService();
        BigDecimal money = new BigDecimal(request.getParameter("money"));
        HttpSession session = request.getSession();
        Passenger passenger = (Passenger) session.getAttribute("user");
        passenger.setMoney(passenger.getMoney().add(money));
        passengerService.addMoney(passenger.getId(),money);
        try {
            response.sendRedirect("/balance.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
