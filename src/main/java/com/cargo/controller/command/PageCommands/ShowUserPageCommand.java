package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import com.cargo.model.entity.Cargo;
import com.cargo.model.entity.User;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowUserPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ShowUserPageCommand.class);
    private final CargoService cargoService;
    private final UserService userService;

    public ShowUserPageCommand(CargoService cargoService, UserService userService) {
        this.cargoService = cargoService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        int page = 1;
        int recordsPerPage = 10;
        int userId = (int) session.getAttribute("currentUserId");

        User user = userService.findUserById(userId);
        session.setAttribute("balance", user.getBalance());

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Cargo> list = cargoService.getAllCargoForUserByIdWithLimit(userId, (page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = cargoService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        LOGGER.info("Show cargos page loaded successfully");

        return Path.PAGE_SHOW_CARGOS;
    }
}