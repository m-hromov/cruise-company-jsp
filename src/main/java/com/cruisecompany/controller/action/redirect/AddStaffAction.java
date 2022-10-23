package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStaffAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");

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

        try {
            StaffService staffService = serviceFactory.getStaffService();
            staffService.addStaff(staff);
            return new ActionMethod("/cruise/add_staff", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}