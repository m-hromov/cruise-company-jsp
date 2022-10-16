package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.exception.ServiceException;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.UserAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditProfileAction implements Action {
    final static Logger logger = LogManager.getLogger(EditProfileAction.class);

    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = (ServiceFactory) request.getServletContext()
                .getAttribute("ServiceFactory");
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/edit_profile.jsp", Method.FORWARD);
        }
        UserAccountService userAccountService = serviceFactory.getUserAccountService();
        PassengerService passengerService = serviceFactory.getPassengerService();
        HttpSession session = request.getSession();

        Passenger passenger = (Passenger) session.getAttribute("user");
        String profilePart = request.getParameter("part");
        if (profilePart.equals("info")) {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            Passenger newPassenger = new Passenger();
            newPassenger.setId(passenger.getId())
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setEmail(email);
            try {
                passengerService.updateProfile(newPassenger);
            } catch (ServiceException e) {
                session.setAttribute("wrongEmail", true);
                return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
            }
            passenger.setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setEmail(email);
        }
        if (profilePart.equals("document")) {
            try {
                String uploadPath = request.getServletContext().getRealPath("") + "resources" + File.separator + "files";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                Part part = request.getPart("document");
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);

                passenger.setDocumentPath("resources/files/" + fileName);
                passengerService.updateDocument(passenger);
            } catch (IOException | ServletException e) {
                logger.error("Unable to save an image!");
                request.getSession().setAttribute("error", 500);
                request.getSession().setAttribute("errorMsg", "Unable to save an image!");
                return new ActionMethod("/cruise/error", Method.REDIRECT);
            } catch (ServiceException e) {
                request.getSession().setAttribute("error", 500);
                request.getSession().setAttribute("errorMsg", "Something went wrong!");
                return new ActionMethod("/cruise/error", Method.REDIRECT);
            }
        }
        if (profilePart.equals("password")) {
            try {
                String oldPassword = request.getParameter("old_password");
                String newPassword = request.getParameter("new_password");
                userAccountService.updatePassword(passenger.getUserAccount().getId(),
                        oldPassword, newPassword);
            } catch (ServiceException e) {
                session.setAttribute("wrongPassword", true);
            }
        }
        return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
    }
}
