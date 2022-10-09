package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStaffAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            ShipService shipService = ServiceFactory.getInstance().getShipService();
            request.setAttribute("listShip", shipService.getAll());
            return new ActionMethod("/WEB-INF/view/add_staff.jsp", Method.FORWARD);

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

        return new ActionMethod("/cruise/add_staff", Method.REDIRECT);

    }
}
