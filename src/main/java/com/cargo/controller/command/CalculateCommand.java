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

public class CalculateCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(CalculateCommand.class);
    private final BranchService branchService;

    public CalculateCommand(BranchService branchService) {
        this.branchService = branchService;
    }

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

        if (isIncorrectCalculateInfo(departureBranchId, destinationBranchId, String.valueOf(weight), String.valueOf(height),
                String.valueOf(length), String.valueOf(width))) {
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