package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.impl.CruiseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddShipAction implements Action {
    final static Logger logger = LogManager.getLogger(CruiseServiceImpl.class);
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/add_ship.jsp", Method.FORWARD);
        }

        Ship ship = new Ship();
        String name = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        try {
            String uploadPath = request.getServletContext().getRealPath("") + "resources" + File.separator + "images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            Part part = request.getPart("photo");
            String fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);

            ship.setName(name)
                    .setPassengerCapacity(capacity)
                    .setPhotoPath("resources/images/" + fileName);

            ShipService shipService = ServiceFactory.getInstance().getShipService();
            shipService.addShip(ship);
            return new ActionMethod("/cruise/add_ship", Method.REDIRECT);
        } catch (IOException | ServletException e) {
            logger.error("Unable to save an image!");
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Unable to save an image!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }

    }
}
