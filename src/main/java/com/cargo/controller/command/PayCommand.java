package com.cargo.controller.command;

import com.cargo.model.entity.Cargo;
import com.cargo.model.entity.User;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The PayCommand class is responsible for handling the process of paying for a cargo delivery.
 *
 * @see Command
 * @see Cargo
 * @see CargoService
 * @see BranchService
 * @see UserService
 */
public class PayCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(PayCommand.class);
    private final CargoService cargoService;
    private final UserService userService;

    public PayCommand(CargoService cargoService, UserService userService) {
        this.cargoService = cargoService;
        this.userService = userService;
    }

    /**
     * Themethod implements the functionality of paying for a cargo delivery.
     * This method retrieves the cargo id and user id from the session, retrieves the relevant cargo and user objects,
     * checks if the user has enough balance to pay for the cargo, and updates the user's balance and the invoice status of the cargo.
     *
     * @param request  The HttpServletRequest instance used to retrieve information from the user request.
     * @param response The HttpServletResponse instance used to send a response back to the user.
     * @return A string representing the cargo page to redirect to.
     * @throws IOException  If an input or output error is detected.
     * @throws SQLException If a database error is detected.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int cargoID = (int) session.getAttribute("session_current_pay_id");
        int userId = (int) session.getAttribute("currentUserId");
        int sessionUserBalance = (int) session.getAttribute("balance");

        Cargo cargo = cargoService.getCargoById(cargoID);
        User user = userService.findUserById(userId);

        String invoiceStatus = String.valueOf(cargo.getInvoiceStatus());

        int balance = user.getBalance();
        int price = cargo.getPrice();

        try {
            if (sessionUserBalance == balance && balance >= price && invoiceStatus.equals("PENDING")) {
                int difBalance = balance - price;
                userService.changeBalance((difBalance), userId);
                cargoService.changeInvoiceStatus(cargoID);

                session.setAttribute("balance", difBalance);

                LOGGER.info("Pay for cargo " + cargoID);
            } else {
                LOGGER.info("Cant pay for " + cargoID + ", not enough balance to pay: " + balance);
            }
        } catch (Exception e) {
            LOGGER.info("Cant pay for " + cargoID + ", not enough balance to pay: " + balance);
        }

        return "redirect:controller?action=showcargospage";
    }
}
