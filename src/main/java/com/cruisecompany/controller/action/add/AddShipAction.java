package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddShipAction implements Action {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return new ActionMethod("/cruise/add_ship", Method.REDIRECT);

    }
}
