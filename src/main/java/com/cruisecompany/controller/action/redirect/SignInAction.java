package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.dto.UserAccountDTO;
import com.cruisecompany.exception.AuthorizationException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInAction implements Action {

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Optional<PassengerDTO> optional = userAccountService.signIn(email, password);
            optional.ifPresentOrElse(passengerDTO -> {
                session.setAttribute("role", "USER");
                session.setAttribute("user", passengerDTO);
            },() -> {
                session.setAttribute("role", "ADMIN");
            });
            return new ActionMethod("/", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (AuthorizationException e) {
            session.setAttribute("error", true);
            return new ActionMethod("/cruise/sign_in", Method.REDIRECT);
        }
    }
}
