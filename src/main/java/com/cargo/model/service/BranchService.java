package com.cargo.model.service;

import com.cargo.model.dao.BranchDao;

import com.cargo.model.entity.Branch;

import java.sql.SQLException;
import java.util.List;

public class BranchService {
    private final BranchDao branchDao;

    public BranchService(BranchDao branchDao) {
        this.branchDao = branchDao;
    }


    public List<Branch> getAllBranches() throws SQLException {
        return branchDao.getAllBranches();
    }

    public Branch getBranchById(int id) throws SQLException {
        return branchDao.getBranchById(id);
    }

    public Branch getBranchByCity(String city) throws SQLException {
        return branchDao.getBranchByCity(city);
    }
}