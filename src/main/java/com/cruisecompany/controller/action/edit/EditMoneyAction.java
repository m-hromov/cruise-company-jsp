package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class EditMoneyAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/balance.jsp", Method.FORWARD);
        }
        PassengerService passengerService = ServiceFactory.getInstance().getPassengerService();
        BigDecimal money = new BigDecimal(request.getParameter("money"));
        HttpSession session = request.getSession();
        Passenger passenger = (Passenger) session.getAttribute("user");
        passenger.setMoney(passenger.getMoney().add(money));
        passengerService.addMoney(passenger.getId(),money);
        return new ActionMethod("/cruise/edit_money", Method.REDIRECT);
    }
}
