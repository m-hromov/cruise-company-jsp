package com.cruisecompany.controller.action.add;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.entity.Ship;
import com.cruisecompany.entity.Staff;
import com.cruisecompany.entity.Station;
import com.cruisecompany.service.ServiceFactory;
import com.cruisecompany.service.ShipService;
import com.cruisecompany.service.StaffService;
import com.cruisecompany.service.StationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStationAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().isEmpty()) {
            try {
                request.getRequestDispatcher("/add_station.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        Station station = new Station();
        station.setCity(city)
                .setCountry(country);

        StationService stationService = ServiceFactory.getInstance().getStationService();
        stationService.addStation(station);
        try {
            response.sendRedirect("/cruise/add_station");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
