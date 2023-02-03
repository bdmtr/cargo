package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.Branch;
import com.cargo.model.service.BranchService;
import com.cargo.util.PriceMaker;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.util.Validator.isIncorrectCalculateInfo;

/**
 * The CalculateCommand class is a command implementation to handle the calculation of delivery price.
 * The class uses the {@link BranchService} for getting {@link Branch} entities by its ID.
 * It also uses the {@link PriceMaker} for getting the distance and price for delivering.
 *
 * @see Command
 * @see BranchService
 * @see Branch
 * @see PriceMaker
 */
public class CalculateCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(CalculateCommand.class);
    private final BranchService branchService;

    public CalculateCommand(BranchService branchService) {
        this.branchService = branchService;
    }

    /**
     * This method {@code execute} calculates the price of a delivery by retrieving information
     * such as departure branch, destination branch, weight, length, height, and width from
     * the {@code HttpServletRequest} object. It uses the information to get the distance between
     * the departure and destination branches and then calculates the price using the {@code PriceMaker} class.
     * Finally, it sets the calculated values as attributes in the {@code HttpServletRequest} object
     * and returns a path to the price page.
     *
     * @param request  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @return a string representation of the path to the price page
     * @throws IOException  if an input or output exception occurred
     * @throws SQLException if a database access error or other errors occurred
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int departureBranchId = Integer.parseInt(request.getParameter("departureBranchId"));
        int destinationBranchId = Integer.parseInt(request.getParameter("destinationBranchId"));

        Branch departureBranch = branchService.getBranchById(departureBranchId);
        Branch destinationBranch = branchService.getBranchById(destinationBranchId);

        int weight = Integer.parseInt((request.getParameter("weight")));
        int length = Integer.parseInt((request.getParameter("height")));
        int height = Integer.parseInt((request.getParameter("length")));
        int width = Integer.parseInt((request.getParameter("width")));

        if (isIncorrectCalculateInfo(departureBranchId, destinationBranchId, weight, height,
                length, width)) {
            return Path.PAGE_CALCULATOR;
        }

        String originsName = String.valueOf(departureBranch.getCity());
        String destinationsName = String.valueOf(destinationBranch.getCity());
        PriceMaker priceMaker = new PriceMaker();
        int distance = priceMaker.getDistance(originsName, destinationsName);
        int price = priceMaker.getPrice(distance, weight, length, height, width);

        request.setAttribute("departureBranch", departureBranch);
        request.setAttribute("destinationBranch", destinationBranch);
        request.setAttribute("weight", weight);
        request.setAttribute("length", length);
        request.setAttribute("height", height);
        request.setAttribute("width", width);
        request.setAttribute("price", price);

        LOGGER.info("Calculated successfully");
        return Path.PAGE_PRICE;
    }
}