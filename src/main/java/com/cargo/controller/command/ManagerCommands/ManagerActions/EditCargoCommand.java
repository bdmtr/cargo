package com.cargo.controller.command.ManagerCommands.ManagerActions;

import com.cargo.controller.command.Command;
import com.cargo.model.CargoDao;
import com.cargo.model.entity.Cargo;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditCargoCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditCargoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        CargoDao cargoDao = new CargoDao();
        int cargoID = (int) session.getAttribute("currentCargoId");
        Cargo cargo = cargoDao.getCargoById(cargoID);
        String receiverFullname = request.getParameter("receiverFullname");
        String deliveryStatus = request.getParameter("deliveryStatus");
        String invoiceStatus = request.getParameter("invoiceStatus");
        cargo.setReceiverFullname(receiverFullname);
        cargo.setDeliveryStatus(DeliveryStatus.valueOf(deliveryStatus));
        cargo.setInvoiceStatus(InvoiceStatus.valueOf(invoiceStatus));

        cargoDao.updateCargoProfile(cargo);

        LOGGER.info("Editing cargo");
        return "redirect:controller?action=showmanagerpage";

    }
}