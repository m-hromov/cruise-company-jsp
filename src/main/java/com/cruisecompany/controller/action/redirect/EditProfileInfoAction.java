package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileInfoAction implements Action {
    final static Logger logger = LogManager.getLogger(EditProfileInfoAction.class);

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        PassengerService passengerService = serviceFactory.getPassengerService();
        HttpSession session = request.getSession();
        try {
            PassengerDTO newPassengerDTO = mapPassengerDTO(request);
            passengerService.updateProfile(newPassengerDTO);
            session.setAttribute("user", newPassengerDTO);
            return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
        } catch (ServiceException e) {
            session.setAttribute("wrongEmail", true);
            return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
        }
    }

    private PassengerDTO mapPassengerDTO(HttpServletRequest request) {
        PassengerDTO passengerDTO = (PassengerDTO) request.getSession().getAttribute("user");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        return new PassengerDTO(passengerDTO)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email);
    }
}
