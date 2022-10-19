package com.cruisecompany.controller.action.forward;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadAddCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");

            ShipService shipService = serviceFactory.getShipService();
            StationService stationService = serviceFactory.getStationService();
            request.setAttribute("listShip", shipService.getAll());
            request.setAttribute("listStation", stationService.getAll());
            return new ActionMethod("/WEB-INF/view/add_cruise.jsp", Method.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Unable to load page!");
            return new ActionMethod("/WEB-INF/view/error.jsp", Method.FORWARD);
        }
    }
}
