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

/**
 * EditCargoPageCommand class is responsible for loading the edit cargo page.
 */
public class EditCargoPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditCargoPageCommand.class);
    private final CargoService cargoService;

    public EditCargoPageCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    /**
     * Loads the cargo editing page.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @return the path to the cargo editing page
     * @throws IOException  if an I/O error occurs
     * @throws SQLException if a database error occurs
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("status_id"));
        HttpSession session = request.getSession();
        Cargo cargo = cargoService.getCargoById(id);
        int currentCargoId = cargo.getId();

        session.setAttribute("session_current_editId", currentCargoId);
        request.setAttribute("currentCargo", cargo);

        LOGGER.info("Edit page loaded successfully");
        return Path.PAGE_CHANGE_STATUS;
    }
}
