package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStaffAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            try {
                ShipService shipService = ServiceFactory.getInstance().getShipService();
                request.setAttribute("listShip", shipService.getAll());
                request.getRequestDispatcher("/add_staff.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String speciality = request.getParameter("speciality");
        long shipId = Long.parseLong(request.getParameter("ship"));
        Staff staff = new Staff();
        staff.setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setSpeciality(speciality)
                .setShip(new Ship().setId(shipId));

        StaffService staffService = ServiceFactory.getInstance().getStaffService();
        staffService.addStaff(staff);
        try {
            response.sendRedirect("/cruise/add_staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
