package com.cruisecompany.controller.action.authorization;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class SignUpAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserAccount userAccount = new UserAccount();
        userAccount.setLogin(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setRole("USER");
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getParameter("fname"))
                .setLastName(request.getParameter("lname"))
                .setPhone(request.getParameter("phone"))
                .setEmail(request.getParameter("email"))
                .setMoney(BigDecimal.ZERO)
                .setUserAccount(userAccount);

        UserAccountService userAccountService = ServiceFactory.getInstance().getUserAccountService();
        userAccountService.signUp(passenger);
        HttpSession session = request.getSession();
        session.setAttribute("role",userAccount.getRole());
        session.setAttribute("user",passenger);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
