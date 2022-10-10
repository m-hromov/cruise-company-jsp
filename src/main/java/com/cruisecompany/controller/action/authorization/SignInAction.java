package com.cruisecompany.controller.action.authorization;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.db.dto.UserAccountDTO;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInAction implements Action {
    UserAccountService userAccountService;
    PassengerService passengerService;

    public SignInAction() {
        userAccountService = ServiceFactory.getInstance().getUserAccountService();
        passengerService = ServiceFactory.getInstance().getPassengerService();
    }

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/sign_in.jsp", Method.FORWARD);
        }

        String login = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember_me");

        try {
            Optional<UserAccountDTO> optional = userAccountService.signIn(login, password);
            if (optional.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("error", true);
                return new ActionMethod("/cruise/sign_in", Method.REDIRECT);

            }

            UserAccountDTO userAccountDTO = optional.get();
            HttpSession session = request.getSession();
            session.setAttribute("role", userAccountDTO.getRole());
            if (userAccountDTO.getRole().equals("USER")) {
                session.setAttribute("user",
                        passengerService.getPassengerByAccountId(userAccountDTO.getId()));
            }
            return new ActionMethod("/", Method.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        }
    }
}
