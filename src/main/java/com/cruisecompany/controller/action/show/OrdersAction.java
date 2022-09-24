package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.db.dto.PassengerOrderDTO;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            PassengerService passengerService = ServiceFactory.getInstance().getPassengerService();
            List<PassengerOrderDTO> passengerOrderDTOList = passengerService.getAllPassengerOrderDTOList();
            request.setAttribute("orderList", passengerOrderDTOList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/orders.jsp");
            requestDispatcher.forward(request,response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
