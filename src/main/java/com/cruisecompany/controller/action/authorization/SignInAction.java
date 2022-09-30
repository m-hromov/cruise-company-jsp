package com.cruisecompany.controller.action.authorization;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class SignInAction implements Action {
    UserAccountService userAccountService;
    PassengerService passengerService;

    public SignInAction() {
        userAccountService = ServiceFactory.getInstance().getUserAccountService();
        passengerService = ServiceFactory.getInstance().getPassengerService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter("email");
            String password = request.getParameter("password");
            String rememberMe = request.getParameter("remember_me");

            Optional<UserAccountDTO> optional = userAccountService.signIn(login, password);
            if (!optional.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute("role", "ADMIN");
                response.sendRedirect("/");
                return;
            }

            UserAccountDTO userAccountDTO = optional.get();
            HttpSession session = request.getSession();
            session.setAttribute("role", userAccountDTO.getRole());
            if (userAccountDTO.getRole().equals("USER")) {
                session.setAttribute("user",
                        passengerService.getPassengerByAccountId(userAccountDTO.getId()));
            }
            response.sendRedirect("/");

        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
