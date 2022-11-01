package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.exception.WrongPasswordException;
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
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        HttpSession session = request.getSession();
        try {
            PassengerDTO passengerDTO = (PassengerDTO) request.getSession().getAttribute("user");
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            userAccountService.updatePassword(passengerDTO.getUserAccountId(),
                    oldPassword, newPassword);
        } catch (ServiceException e) {
            session.setAttribute("error",500);
            session.setAttribute("errorMsg","Unable to sign in");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (WrongPasswordException e) {
            session.setAttribute("wrongPassword", true);
        }
        return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
    }
}
