package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.BranchDao;
import com.cargo.model.entity.Branch;
import com.cargo.util.PriceMaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.util.Validator.isIncorrectCalculateInfo;

public class CalculateCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();

        int departureBranchId = Integer.parseInt(request.getParameter("departureBranchId"));
        int destinationBranchId = Integer.parseInt(request.getParameter("destinationBranchId"));

        Branch departureBranch = BranchDao.getInstance().getBranchById(departureBranchId);
        Branch destinationBranch = BranchDao.getInstance().getBranchById(destinationBranchId);

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


        session.setAttribute("departureBranch", departureBranch);
        session.setAttribute("destinationBranch", destinationBranch);
        session.setAttribute("weight", weight);
        session.setAttribute("length", length);
        session.setAttribute("height", height);
        session.setAttribute("width", width);
        session.setAttribute("price", price);

        return Path.PAGE_PRICE;
    }
}