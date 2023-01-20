package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import com.cargo.model.CargoDao;
import com.cargo.model.entity.Cargo;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditCargoPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditCargoPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        CargoDao cargoDao = new CargoDao();
        Cargo cargo = cargoDao.getCargoById(id);
        int currentCargoId = cargo.getId();
        session.setAttribute("currentCargoId", currentCargoId);
        session.setAttribute("currentCargo", cargo);

        LOGGER.info("Edit page loaded successfully");

        return Path.PAGE_CHANGE_STATUS;

    }
}
