package com.cruisecompany.controller.action.redirect;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.dto.mapper.DTOMapper;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.entity.UserAccount;
import com.cruisecompany.exception.EmailAlreadyExistsException;
import com.cruisecompany.exception.RecaptchaException;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import com.cruisecompany.util.recaptcha.RecaptchaVerifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class SignUpAction implements Action {
    private static final String URL;
    private static final String SECRET_KEY;
    static{
        try {
            Properties properties = new Properties();
            properties.load(SignInAction.class.getClassLoader().getResourceAsStream("app.properties"));
            URL = properties.getProperty("recaptcha.siteverify");
            SECRET_KEY = properties.getProperty("recaptcha.signup.secret");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        HttpSession session = request.getSession();
        UserAccountService userAccountService = serviceFactory.getUserAccountService();

        try {
            String gRecaptcha = request.getParameter("g-recaptcha-response");
            RecaptchaVerifier.verify(gRecaptcha,URL,SECRET_KEY);

            Passenger passenger = mapPassenger(request);
            userAccountService.signUp(passenger);
            session.setAttribute("role","USER");
            session.setAttribute("user", DTOMapper.toPassengerDTO(passenger));
            return new ActionMethod("/", Method.REDIRECT);
        } catch (RecaptchaException | ServiceException e) {
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
        userAccount.setEmail(email)
                .setPassword(request.getParameter("password"))
                .setRole("USER");
        return new Passenger()
                .setFirstName(request.getParameter("first_name"))
                .setLastName(request.getParameter("last_name"))
                .setPhone(request.getParameter("phone"))
                .setMoney(BigDecimal.ZERO)
                .setUserAccount(userAccount);
    }
}
