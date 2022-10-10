package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            PassengerService passengerService = ServiceFactory.getInstance().getPassengerService();
            List<PassengerOrderDTO> passengerOrderDTOList = passengerService.getAllPassengerOrderDTOList();
            request.setAttribute("orderList", passengerOrderDTOList);
            return new ActionMethod("/WEB-INF/view/orders.jsp", Method.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/WEB-INF/view/error.jsp", Method.FORWARD);
        }
    }

}
