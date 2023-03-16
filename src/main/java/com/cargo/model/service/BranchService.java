package com.cargo.model.service;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.BranchDao;

import com.cargo.model.entity.Branch;

import java.util.List;

public class BranchService {
    private final BranchDao branchDao;

    public BranchService(BranchDao branchDao) {
        this.branchDao = branchDao;
    }


    public List<Branch> getAllBranches() throws DaoException {
        return branchDao.getAllBranches();
    }

    public Branch getBranchById(int id) throws DaoException {
        return branchDao.getBranchById(id);
    }

    public Branch getBranchByCity(String city) throws DaoException {
        return branchDao.getBranchByCity(city);
    }
}