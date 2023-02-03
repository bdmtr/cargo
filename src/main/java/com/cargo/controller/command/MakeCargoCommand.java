package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import com.cargo.util.PriceMaker;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.cargo.util.Validator.isIncorrectCargoInfo;

/**
 * MakeCargoCommand handles the creation of a cargo.
 *
 * @see Command
 * @see Cargo
 * @see CargoService
 * @see BranchService
 * @see UserService
 */
public class MakeCargoCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(MakeCargoCommand.class);
    private final CargoService cargoService;
    private final BranchService branchService;
    private final UserService userService;

    public MakeCargoCommand(CargoService cargoService, BranchService branchService, UserService userService) {
        this.cargoService = cargoService;
        this.branchService = branchService;
        this.userService = userService;
    }

    /**
     * Handles the creation of a cargo.
     * The method retrieves data from the request parameters such as the cargo type, receiver full name, departure and destination branches, weight, length, height, width.
     * Then it performs validation for the provided information and calculates the distance and price for the cargo.
     * The method then adds the cargo to the database using the cargoService.
     *
     * @param request  the HTTP request.
     * @param response the HTTP response.
     * @return the path to cargo page.
     * @throws IOException  if an input or output exception occurs.
     * @throws SQLException if a database exception occurs.
     */
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
        cargo.setUser(userService.findUserById((Integer) session.getAttribute("currentUserId")));
        cargo.setReceiverFullname(receiverFullname);
        cargo.setDepartureBranch(originsBranch);
        cargo.setDestinationBranch(destinationsBranch);
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

        //  return Path.PAGE_SHOW_CARGOS;
        return "redirect:controller?action=showcargospage";
    }

}