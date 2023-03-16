package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import com.cargo.exceptions.DaoException;
import com.cargo.model.entity.Cargo;
import com.cargo.model.service.CargoService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * The ShowGuestPageCommand class implements the Command interface and represents the command to show the search result page for the guest users.
 *
 * @see Command
 * @see CargoService
 * @see Cargo
 */
public class ShowGuestPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ShowGuestPageCommand.class);
    private final CargoService cargoService;

    public ShowGuestPageCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    /**
     * Executes the command to show the search result page for the guest users.
     *
     * @param request  an HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
     * @return a string that represents the guest page.
     * @throws IOException  if an input or output exception occurs.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int page = 1;
        int recordsPerPage = 6;
        List<Cargo> list;

        String searchBranchId = request.getParameter("req_branch_id");
        if (searchBranchId == null || searchBranchId.isEmpty()) {
            searchBranchId = (String) session.getAttribute("session_branch_id");
        }

        String searchOrder = request.getParameter("req_order");
        if (searchOrder == null || searchOrder.isEmpty()) {
            searchOrder = (String) session.getAttribute("session_order");
        }

        session.setAttribute("session_order", searchOrder);
        session.setAttribute("session_branch_id", searchBranchId);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            list = cargoService.sortByCityDate((page - 1) * recordsPerPage, recordsPerPage, searchBranchId, searchOrder);
        } catch (DaoException e) {
            LOGGER.error("Cant sort cities");
            return Path.PAGE_LOGIN;
        }


        int noOfRecords = cargoService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        LOGGER.info("Search cities for guest");
        return Path.PAGE_SHOW_SEARCH;
    }
}