package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfilePasswordAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                    .getAttribute("ServiceFactory");
            UserAccountService userAccountService = serviceFactory.getUserAccountService();

            PassengerDTO passengerDTO = (PassengerDTO) request.getSession().getAttribute("user");
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            userAccountService.updatePassword(passengerDTO.getUserAccountId(),
                    oldPassword, newPassword);
        } catch (ServiceException e) {
            request.getSession().setAttribute("wrongPassword", true);
        }
        return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
    }
}
