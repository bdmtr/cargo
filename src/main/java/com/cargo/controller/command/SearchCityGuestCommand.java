package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.CargoDao;
import com.cargo.model.entity.Cargo;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchCityGuestCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(SearchCityGuestCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 10;

        CargoDao cargoDao = new CargoDao();

        String searchBranchId = request.getParameter("req_branch_id");
        if (searchBranchId == null || searchBranchId.isEmpty()) {
            searchBranchId = (String) session.getAttribute("session_branch_id");
        }

        String searchOrder = request.getParameter("req_order");
        if (searchOrder== null || searchOrder.isEmpty()) {
            searchOrder = (String) session.getAttribute("session_order");
        }

        session.setAttribute("session_order", searchOrder);
        session.setAttribute("session_branch_id", searchBranchId);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Cargo> list = cargoDao.sortByCityDate((page - 1) * recordsPerPage, recordsPerPage, searchBranchId, searchOrder);
        int noOfRecords = cargoDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        LOGGER.info("Search cities for guest");
        return Path.PAGE_SHOW_SEARCH;
    }
}