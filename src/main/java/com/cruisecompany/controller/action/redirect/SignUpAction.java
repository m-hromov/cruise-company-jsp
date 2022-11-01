package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.EmailAlreadyExistsException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SignUpAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        HttpSession session = request.getSession();
        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        try {
            Passenger passenger = mapPassenger(request);
            userAccountService.signUp(passenger);
            session.setAttribute("role","USER");
            session.setAttribute("user", DTOMapper.toPassengerDTO(passenger));
            return new ActionMethod("/", Method.REDIRECT);
        } catch (ServiceException e) {
            session.setAttribute("error",500);
            session.setAttribute("errorMsg","Unable to sign up");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (EmailAlreadyExistsException e) {
            session.setAttribute("emailExists",true);
            return new ActionMethod("/cruise/sign_up", Method.REDIRECT);
        }
    }

    private Passenger mapPassenger(HttpServletRequest request) {
        UserAccount userAccount = new UserAccount();
        String email = request.getParameter("email");
        userAccount.setLogin(email)
                .setPassword(request.getParameter("password"))
                .setRole("USER");
        return new Passenger()
                .setFirstName(request.getParameter("first_name"))
                .setLastName(request.getParameter("last_name"))
                .setPhone(request.getParameter("phone"))
                .setEmail(request.getParameter("email"))
                .setMoney(BigDecimal.ZERO)
                .setUserAccount(userAccount);
    }
}
