package com.cruisecompany.controller.action.authorization;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class SignUpAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/sign_up.jsp", Method.FORWARD);
        }
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

        UserAccountService userAccountService = ServiceFactory.getInstance().getUserAccountService();
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
