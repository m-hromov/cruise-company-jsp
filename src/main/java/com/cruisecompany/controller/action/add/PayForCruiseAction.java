package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class PayForCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter("order_id"));
        try {
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            BigDecimal paid = orderService.pay(orderId);
            if (paid.longValue() < 0) {
                session.setAttribute("lowMoney", true);
                return new ActionMethod("/cruise/user_orders", Method.REDIRECT);

            }
            Passenger passenger = (Passenger) session.getAttribute("user");
            passenger.setMoney(paid);
            return new ActionMethod("/cruise/user_orders", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }

    }
}
