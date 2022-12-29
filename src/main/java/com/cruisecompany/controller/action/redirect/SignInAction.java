package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.PassengerDTO;
import com.cruisecompany.exception.AuthorizationException;
import com.cruisecompany.exception.RecaptchaException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.recaptcha.RecaptchaVerifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class SignInAction implements Action {
    private static final String URL;
    private static final String SECRET_KEY;
    static{
        try {
            Properties properties = new Properties();
            properties.load(SignInAction.class.getClassLoader().getResourceAsStream("app.properties"));
            URL = properties.getProperty("recaptcha.siteverify");
            SECRET_KEY = properties.getProperty("recaptcha.signin.secret");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        HttpSession session = request.getSession();
        try {
            String gRecaptcha = request.getParameter("g-recaptcha-response");
            RecaptchaVerifier.verify(gRecaptcha,URL,SECRET_KEY);

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Optional<PassengerDTO> optional = userAccountService.signIn(email, password);
            optional.ifPresentOrElse(passengerDTO -> {
                session.setAttribute("role", passengerDTO.getRole());
                session.setAttribute("user", passengerDTO);
            },() -> {
                session.setAttribute("role", "ADMIN");
            });
            return new ActionMethod("/", Method.REDIRECT);
        } catch (RecaptchaException | ServiceException e) {
            request.getSession().setAttribute("error", 500);
            request.getSession().setAttribute("errorMsg", "Something went wrong!");
            return new ActionMethod("/cruise/error", Method.REDIRECT);
        } catch (AuthorizationException e) {
            session.setAttribute("error", true);
            return new ActionMethod("/cruise/sign_in", Method.REDIRECT);
        }
    }
}
