package com.cruisecompany.controller.action.show;

import com.cruisecompany.controller.action.Action;
import com.cruisecompany.controller.action.ActionMethod;
import com.cruisecompany.controller.action.Method;
import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.service.CruiseService;
import com.cruisecompany.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindCruiseAction implements Action {
    @Override
    public ActionMethod execute(HttpServletRequest request, HttpServletResponse response) {
        CruiseService cruiseService = ServiceFactory.getInstance().getCruiseService();
        LocalDate dateFrom = LocalDate.now().minusYears(5);
        LocalDate dateTo = LocalDate.now().plusYears(5);
        int durationFrom = 1;
        int durationTo = 730;
        int limit = 5;
        int offset = 0;
        int page = 1;
        String duration = null;

        Optional<String> searchById = Optional.ofNullable(request.getParameter("cruise_id"));
        List<CruiseShowDTO> cruiseList = new ArrayList<>();
        if (searchById.isPresent()) {
            Optional<CruiseShowDTO> optionalCruise = cruiseService.getCruiseShowDTOById(Long.parseLong(searchById.get()));
            if (optionalCruise.isEmpty()) {
                request.setAttribute("error", true);
            } else {
                cruiseList.add(optionalCruise.get());
            }
            request.setAttribute("listCruise", cruiseList);
            return new ActionMethod("/WEB-INF/view/find_cruise.jsp", Method.FORWARD);
        }

        Optional<String> dateFromOptional = Optional.ofNullable(request.getParameter("dateFrom"));
        Optional<String> dateToOptional = Optional.ofNullable(request.getParameter("dateTo"));
        Optional<String> durationOptional = Optional.ofNullable(request.getParameter("duration"));
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter("page"));
        Optional<String> limitOptional = Optional.ofNullable(request.getParameter("limit"));

        if (dateFromOptional.isPresent()) dateFrom = LocalDate.parse(dateFromOptional.get());
        if (dateToOptional.isPresent()) dateTo = LocalDate.parse(dateToOptional.get());
        if (durationOptional.isPresent()) {
            duration = durationOptional.get();
            if (duration.equals("1")) {
                durationTo = 5;
            }
            if (duration.equals("2")) {
                durationFrom = 6;
                durationTo = 9;
            }
            if (duration.equals("3")) {
                durationFrom = 10;
            }
        }
        if (limitOptional.isPresent()) limit = Integer.parseInt(limitOptional.get());
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
            offset = page * limit - limit;
        }

        cruiseList = cruiseService.getAllFilteredCruiseShowDTO(dateFrom, dateTo, durationFrom, durationTo, limit, offset);
        long pageAmount = cruiseService.getPageAmount(dateFrom, dateTo, durationFrom, durationTo, limit);
        LocalDate currentDate = LocalDate.now();
        request.setAttribute("currentDate", currentDate);
        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);
        request.setAttribute("duration", duration);
        request.setAttribute("limit", limit);
        request.setAttribute("page", page);
        request.setAttribute("listCruise", cruiseList);
        request.setAttribute("pageAmount", pageAmount);
        return new ActionMethod("/WEB-INF/view/find_cruise.jsp", Method.FORWARD);

    }
}
