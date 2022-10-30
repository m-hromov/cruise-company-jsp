package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class EditMoneyAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            PassengerService passengerService = serviceFactory.getPassengerService();
            HttpSession session = request.getSession();

            BigDecimal money = new BigDecimal(request.getParameter("money"));
            PassengerDTO passengerDTO = (PassengerDTO) session.getAttribute("user");
            passengerService.addMoney(passengerDTO, money);
            return new ActionMethod("/cruise/edit_money", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}
