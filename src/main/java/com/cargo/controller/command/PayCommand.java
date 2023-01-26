package com.cargo.controller.command;

import com.cargo.controller.Path;
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

public class PayCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(PayCommand.class);
    private final CargoService cargoService;
    private final UserService userService;

    public PayCommand(CargoService cargoService, UserService userService) {
        this.cargoService = cargoService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int cargoID = (int) session.getAttribute("session_current_pay_id");
        int userId = (int) session.getAttribute("currentUserId");

        Cargo cargo = cargoService.getCargoById(cargoID);
        User user = userService.findUserById(userId);

        String invoiceStatus = String.valueOf(cargo.getInvoiceStatus());

        int balance = user.getBalance();
        int price = cargo.getPrice();

        if (balance >= price && invoiceStatus.equals("PENDING")) {
            int difBalance = balance-price;
            cargoService.changeInvoiceStatus(cargoID);
            userService.changeBalance((difBalance), userId);

            session.setAttribute("balance", difBalance);

            LOGGER.info("Pay for cargo " + cargoID);
        } else {
            LOGGER.info("cant pay " + (balance - price));
        }

        return Path.PAGE_SHOW_CARGOS;

        //return "redirect:controller?action=showcargospage";
    }
}
