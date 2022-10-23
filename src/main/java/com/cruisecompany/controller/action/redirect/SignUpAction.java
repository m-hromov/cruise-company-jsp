package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
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
        UserAccount userAccount = new UserAccount();
        userAccount.setLogin(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setRole("USER");
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getParameter("first_name"))
                .setLastName(request.getParameter("last_name"))
                .setPhone(request.getParameter("phone"))
                .setEmail(request.getParameter("email"))
                .setMoney(BigDecimal.ZERO)
                .setUserAccount(userAccount);

        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        try {
            userAccountService.signUp(passenger);
            HttpSession session = request.getSession();
            session.setAttribute("role",userAccount.getRole());
            session.setAttribute("user",passenger);
            return new ActionMethod("/", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error",500);
            request.getSession().setAttribute("errorMsg","Unable to sign up");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}