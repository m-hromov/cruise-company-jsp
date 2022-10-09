package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.service.OrderService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmOrderAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        long orderId = Long.parseLong(request.getParameter("order_id"));
        orderService.confirm(orderId);
        return new ActionMethod("/cruise/orders", Method.REDIRECT);
    }
}
