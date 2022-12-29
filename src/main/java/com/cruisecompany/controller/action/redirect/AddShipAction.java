package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.impl.CruiseServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@Log4j2
public class AddShipAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            ShipService shipService = serviceFactory.getShipService();

            Ship ship = mapShip(request);
            String requestRealPath = request.getServletContext().getRealPath("");
            Part part = request.getPart("photo");

            shipService.addShip(ship, part, requestRealPath);
            return new ActionMethod("/cruise/add_ship", Method.REDIRECT);
        } catch (IOException | ServletException e) {
            log.error("Unable to save an image!");
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Unable to save an image!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }

    }

    private Ship mapShip(HttpServletRequest request) {
        String name = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        return Ship.builder()
                .name(name)
                .passengerCapacity(capacity)
                .build();
    }
}
