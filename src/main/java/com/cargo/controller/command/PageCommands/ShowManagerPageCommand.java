package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import com.cargo.model.entity.Cargo;
import com.cargo.model.service.CargoService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ShowManagerPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ShowManagerPageCommand.class);
    private final CargoService cargoService;

    public ShowManagerPageCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int page = 1;
        int recordsPerPage = 10;

        String searchBranchId = request.getParameter("req_branch_id");

        if (searchBranchId == null || searchBranchId.isEmpty()) {
            searchBranchId = (String) session.getAttribute("session_branch_id");
        }

        String searchDate = request.getParameter("req_date");
        if (searchDate == null || searchDate.length()<1) {
            searchDate = (String) session.getAttribute("session_date");
        }


        String searchOrder = request.getParameter("req_order");
        if (searchOrder == null || searchOrder.isEmpty()) {
            searchOrder = (String) session.getAttribute("session_order");
        }

        session.setAttribute("session_branch_id", searchBranchId);
        session.setAttribute("session_date", searchDate);
        session.setAttribute("session_order", searchOrder);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Cargo> list = cargoService.sortByCityDateManager((page - 1) * recordsPerPage, recordsPerPage, searchBranchId, searchDate, searchOrder);
        int noOfRecords = cargoService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);


        LOGGER.info("Manager page loaded successfully");

        return Path.PAGE_MANAGER;
    }
}