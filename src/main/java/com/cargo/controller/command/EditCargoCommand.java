package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.Cargo;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.model.service.CargoService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditCargoCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditCargoCommand.class);
    private final CargoService cargoService;

    public EditCargoCommand(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int cargoID = (int) session.getAttribute("session_current_editId");
        Cargo cargo = cargoService.getCargoById(cargoID);
        String receiverFullname = request.getParameter("receiverFullname");
        String deliveryStatus = request.getParameter("deliveryStatus");
        String invoiceStatus = request.getParameter("invoiceStatus");
        cargo.setReceiverFullname(receiverFullname);
        cargo.setDeliveryStatus(DeliveryStatus.valueOf(deliveryStatus));
        cargo.setInvoiceStatus(InvoiceStatus.valueOf(invoiceStatus));

        cargoService.updateCargoProfile(cargo);

        LOGGER.info("Editing cargo");
        return "redirect:controller?action=showmanagerpage";
    }
}