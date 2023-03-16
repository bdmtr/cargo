package com.cargo.model.service;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.CargoDao;
import com.cargo.model.entity.Cargo;

import java.sql.SQLException;
import java.util.List;

public class CargoService {
    private CargoDao cargoDao;

    public CargoService(CargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }

    public List<Cargo> getAllCargoForUserByIdWithLimit(int id, int offset, int noOfRecords) {
        return cargoDao.getAllCargoForUserByIdWithLimit(id, offset, noOfRecords);
    }

    public Cargo getCargoById(int id) throws SQLException {
        return cargoDao.getCargoById(id);
    }

    public void addCargo(Cargo cargo) throws SQLException {
        cargoDao.addCargo(cargo);
    }


    public void updateCargoProfile(Cargo cargo) {
        cargoDao.updateCargoProfile(cargo);
    }

    public List<Cargo> sortByCityDate(int offset, int noOfRecords, String branchCity, String order) throws SQLException, DaoException {
        return cargoDao.sortByCityDate(offset, noOfRecords, branchCity, order);
    }

    public List<Cargo> sortByCityDateManager(int offset, int noOfRecords, String destinationBrId, String date, String order) throws SQLException {
        return cargoDao.sortByCityDateManager(offset, noOfRecords,destinationBrId, date, order);
    }

    public int getNoOfRecords() {
        return cargoDao.getNoOfRecords();
    }


    public void changeInvoiceStatus(int id){
        cargoDao.changeInvoiceStatus(id);
    }
}
