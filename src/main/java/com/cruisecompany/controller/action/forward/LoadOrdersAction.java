package com.cruisecompany.controller.action.forward;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.db.dto.PassengerOrderDTO;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadOrdersAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            PassengerService passengerService = serviceFactory.getPassengerService();
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
