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

public class PayPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(PayPageCommand.class);
    private final CargoService cargoService;

    public PayPageCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("pay_id"));

        Cargo cargo = cargoService.getCargoById(id);

        session.setAttribute("session_current_pay_id", id);
        request.setAttribute("currentCargo", cargo);

        LOGGER.info("Pay page loaded successfully");
        return Path.PAGE_PAY;
    }
}
