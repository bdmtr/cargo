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


        String searchBranchDep = request.getParameter("req_branch_dep");
        if (searchBranchDep == null || searchBranchDep.isEmpty()) {
            searchBranchDep = (String) session.getAttribute("session_branch_dep");
        }

        String searchBranchDes = request.getParameter("req_branch_des");
        if (searchBranchDes == null || searchBranchDes.isEmpty()) {
            searchBranchDes = (String) session.getAttribute("session_branch_des");
        }

        String searchDeliveryDate = request.getParameter("req_delivery_date");
        if (searchDeliveryDate == null || searchDeliveryDate.isEmpty()) {
            searchDeliveryDate = (String) session.getAttribute("session_delivery_date");
        }

        String searchOrder = request.getParameter("req_order");
        if (searchOrder == null || searchOrder.isEmpty()) {
            searchOrder = (String) session.getAttribute("session_order");
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }


        List<Cargo> list = cargoService.sortByCityDateManager((page - 1) * recordsPerPage, recordsPerPage, searchBranchDep, searchBranchDes, searchDeliveryDate, searchOrder);
        int noOfRecords = cargoService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("session_order", searchOrder);
        request.setAttribute("session_branch_dep", searchBranchDes);
        request.setAttribute("session_branch_des", searchBranchDep);
        request.setAttribute("session_delivery_date", searchDeliveryDate);


        LOGGER.info("Manager page loaded successfully");

        return Path.PAGE_MANAGER;
    }
}