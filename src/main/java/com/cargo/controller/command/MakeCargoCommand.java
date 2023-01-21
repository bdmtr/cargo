package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.CargoService;
import com.cargo.util.PriceMaker;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.cargo.util.Validator.isIncorrectCargoInfo;

public class MakeCargoCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(MakeCargoCommand.class);
    private final CargoService cargoService;
    private final BranchService branchService;

    public MakeCargoCommand(CargoService cargoService, BranchService branchService) {
        this.cargoService = cargoService;
        this.branchService = branchService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();

        String type = request.getParameter("type");
        String receiverFullname = request.getParameter("receiverFullname");
        int departureBranchId = Integer.parseInt(request.getParameter("departureBranchId"));
        int destinationBranchId = Integer.parseInt(request.getParameter("destinationBranchId"));

        int weight = Integer.parseInt((request.getParameter("weight")));
        int length = Integer.parseInt((request.getParameter("height")));
        int height = Integer.parseInt((request.getParameter("length")));
        int width = Integer.parseInt((request.getParameter("width")));

        if (isIncorrectCargoInfo(type, receiverFullname, departureBranchId,
                destinationBranchId, String.valueOf(weight), String.valueOf(height), String.valueOf(length), String.valueOf(width))) {
            return Path.PAGE_MAKE_CARGO;
        }


        Branch originsBranch = branchService.getBranchById(departureBranchId);
        Branch destinationsBranch = branchService.getBranchById(destinationBranchId);
        String originsName = String.valueOf(originsBranch.getCity());
        String destinationsName = String.valueOf(destinationsBranch.getCity());
        PriceMaker priceMaker = new PriceMaker();
        int distance = priceMaker.getDistance(originsName, destinationsName);
        int price = priceMaker.getPrice(distance, weight, length, height, width);

        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        Timestamp deliveryDate = new Timestamp(System.currentTimeMillis() + 86400000);
        DeliveryStatus deliveryStatus = DeliveryStatus.TRANSIT;
        InvoiceStatus invoiceStatus = InvoiceStatus.PENDING;

        Cargo cargo = new Cargo();

        cargo.setType(type);
        cargo.setUserId((Integer) session.getAttribute("currentUserId"));
        cargo.setReceiverFullname(receiverFullname);
        cargo.setDepartureBranchId(departureBranchId);
        cargo.setDestinationBranchId(destinationBranchId);
        cargo.setPrice(price);
        cargo.setWeight(weight);
        cargo.setLength(length);
        cargo.setHeight(height);
        cargo.setWidth(width);
        cargo.setCreationDate(creationDate);
        cargo.setDeliveryDate(deliveryDate);
        cargo.setDeliveryStatus(deliveryStatus);
        cargo.setInvoiceStatus(invoiceStatus);

        cargoService.addCargo(cargo);

        LOGGER.info("Cargo created");

        // return Path.PAGE_SHOW_CARGOS;
        return "redirect:controller?action=showcargospage";
    }
}