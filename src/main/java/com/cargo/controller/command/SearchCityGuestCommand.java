package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.BranchDao;
import com.cargo.model.CargoDao;
import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchCityGuestCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int page = 1;
        int recordsPerPage = 10;
        int destinationBranchId = 0;
        Branch branch = null;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        BranchDao branchDao = new BranchDao();

        String search_city = request.getParameter("search_branch");
        if (search_city!=null) {
            branch = branchDao.getBranchByCity(search_city);
        }
        if (branch != null) {
            destinationBranchId = branch.getId();
        }

        CargoDao cargoDao = new CargoDao();
        List<Cargo> list = cargoDao.searchGuestCargos((page - 1) * recordsPerPage, recordsPerPage, destinationBranchId);
        int noOfRecords = cargoDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("cargoList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return Path.PAGE_SHOW_SEARCH;
    }
}