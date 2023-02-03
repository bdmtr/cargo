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

/**
 * PayPageCommand class is responsible for loading the pay page.
 */
public class PayPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(PayPageCommand.class);
    private final CargoService cargoService;
    private final UserService userService;

    public PayPageCommand(CargoService cargoService, UserService userService) {
        this.cargoService = cargoService;
        this.userService = userService;
    }

    /**
     * The method retrieves the  data to render the pay page, such as the user's balance and the cargo's price, and sets them as attributes to the request.
     * If the user's balance is not enough to pay for the cargo, the method returns a path to the cargo list page.
     *
     * @param request  the HttpServletRequest object containing user's request information
     * @param response the HttpServletResponse object for sending response to the user
     * @return a path to the pay page or a path to the cargo list page (depends on the balance of the user)
     * @throws IOException  if an input or output error is detected when the servlet handles the request
     * @throws SQLException if a database access error or other errors occurred
     */
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
