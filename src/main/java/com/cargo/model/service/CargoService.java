package com.cargo.model.service;

import com.cargo.model.dao.BranchDao;
import com.cargo.model.dao.CargoDao;
import com.cargo.model.entity.Cargo;

import java.sql.SQLException;
import java.util.List;

public class CargoService {
    private CargoDao cargoDao;

    public CargoService(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    public List<Cargo> getAllCargo() {
        return cargoDao.getAllCargo();
    }

    public List<Cargo> getAllCargoForUserById(int id) {
        return cargoDao.getAllCargoForUserById(id);
    }

    public List<Cargo> getAllCargoForUserByIdWithLimit(int id, int offset, int noOfRecords) {
        return cargoDao.getAllCargoForUserByIdWithLimit(id, offset, noOfRecords);
    }

    public List<Cargo> getAllCargoWithLimit(int offset, int noOfRecords) {
        return getAllCargoWithLimit(offset, noOfRecords);
    }

    public List<Cargo> getAllGuestCargoWithLimit(int offset, int noOfRecords) throws SQLException {
        return cargoDao.getAllGuestCargoWithLimit(offset, noOfRecords);
    }

    public Cargo getCargoById(int id) throws SQLException {
        return cargoDao.getCargoById(id);
    }

    public void addCargo(Cargo cargo) throws SQLException {
        cargoDao.addCargo(cargo);
    }

    public void deleteCargoById(int id) throws SQLException {
        cargoDao.deleteCargoById(id);
    }

    public void deleteCargoByUserId(int id) throws SQLException {
        cargoDao.deleteCargoByUserId(id);
    }

    public void updateCargoProfile(Cargo cargo) {
        cargoDao.updateCargoProfile(cargo);
    }

    public List<Cargo> sortByCityDate(int offset, int noOfRecords, String branchCity, String order) throws SQLException {
        return cargoDao.sortByCityDate(offset, noOfRecords, branchCity, order);
    }

    public List<Cargo> sortByCityDateManager(int offset, int noOfRecords, String departmentBrId, String destinationBrId, String date, String order) throws SQLException {
        return cargoDao.sortByCityDateManager(offset, noOfRecords, departmentBrId, destinationBrId, date, order);
    }

    public int getNoOfRecords() {
        return cargoDao.getNoOfRecords();
    }

}
