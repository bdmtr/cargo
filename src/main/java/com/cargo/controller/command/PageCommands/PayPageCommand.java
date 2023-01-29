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

public class PayPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(PayPageCommand.class);
    private final CargoService cargoService;
    private final UserService userService;

    public PayPageCommand(CargoService cargoService, UserService userService) {
        this.cargoService = cargoService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("pay_id"));
        int userId = (int) session.getAttribute("currentUserId");
        User user = userService.findUserById(userId);
        session.setAttribute("balance", user.getBalance());

        Cargo cargo = cargoService.getCargoById(id);

        session.setAttribute("session_current_pay_id", id);
        request.setAttribute("currentCargo", cargo);

        if (user.getBalance() >= cargo.getPrice()) {
            LOGGER.info("Pay page loaded successfully");
            return Path.PAGE_PAY;
        } else return Path.PAGE_SHOW_CARGOS;
    }
}
