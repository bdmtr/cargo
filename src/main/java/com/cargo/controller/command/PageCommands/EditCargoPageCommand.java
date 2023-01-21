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

public class EditCargoPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditCargoPageCommand.class);
    private final CargoService cargoService;

    public EditCargoPageCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Cargo cargo = cargoService.getCargoById(id);
        int currentCargoId = cargo.getId();
        session.setAttribute("currentCargoId", currentCargoId);
        session.setAttribute("currentCargo", cargo);

        LOGGER.info("Edit page loaded successfully");

        return Path.PAGE_CHANGE_STATUS;

    }
}
