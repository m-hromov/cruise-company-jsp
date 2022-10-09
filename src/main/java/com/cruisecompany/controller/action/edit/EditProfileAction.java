package com.cruisecompany.controller.action.edit;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.entity.Passenger;
import com.cruisecompany.service.PassengerService;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.UserAccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditProfileAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            return new ActionMethod("/WEB-INF/view/edit_profile.jsp", Method.FORWARD);
        }
        UserAccountService userAccountService = ServiceFactory.getInstance().getUserAccountService();
        PassengerService passengerService = ServiceFactory.getInstance().getPassengerService();
        HttpSession session = request.getSession();

        Passenger passenger = (Passenger) session.getAttribute("user");
        String profilePart = request.getParameter("part");
        if(profilePart.equals("info")) {
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
            boolean updated = passengerService.updateProfile(newPassenger);
            if(!updated) {
                session.setAttribute("wrongEmail", true);
                return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
            }
            passenger.setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setEmail(email);
        }
        if(profilePart.equals("document")) {
            try {
                String uploadPath = request.getServletContext().getRealPath("") + "resources" + File.separator + "files";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                Part part = request.getPart("document");
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);

                passenger.setDocumentPath("resources/files/" + fileName);
                passengerService.updateDocument(passenger);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if(profilePart.equals("password")) {
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            boolean updated = userAccountService.updatePassword(passenger.getUserAccount().getId(),
                    oldPassword,newPassword);
            if(!updated) {
                session.setAttribute("wrongPassword", true);
            }
        }
        return new ActionMethod("/cruise/edit_profile", Method.REDIRECT);
    }
}
