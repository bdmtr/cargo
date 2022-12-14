package com.cargo.util;

import com.cargo.dao.BranchDAO;
import com.cargo.dao.CargoDAO;
import com.cargo.dao.UserDAO;
import com.cargo.dao.entity.Branch;
import com.cargo.dao.entity.Cargo;
import com.cargo.dao.entity.User;
import com.cargo.dao.enums.City;
import com.cargo.dao.enums.DeliveryStatus;
import com.cargo.dao.enums.InvoiceStatus;
import com.cargo.dao.enums.Role;

import java.sql.SQLException;
import java.sql.Timestamp;

import static com.cargo.dao.enums.DeliveryStatus.DELIVERED;
import static com.cargo.dao.enums.InvoiceStatus.PAYED;

public class Main {

    private static DataSourceUtil dataSourceUtil;

    public static void main(String[] args) throws SQLException {

        User user = new User();
        user.setUsername("Vovar");
        user.setFullname("Bobarid");
        user.setEmail("gmail3@gmail.com");
        user.setPassword("qwertyr4");
        user.setRole(Role.USER);

        Branch branch = new Branch();
        branch.setCity(City.KYIV);
        branch.setAddress("011101");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long deliveryPause = 10000L;

        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        Timestamp deliveryDate = new Timestamp(System.currentTimeMillis() + deliveryPause);


        Cargo cargo = new Cargo();
        cargo.setType("Goods");
        cargo.setUserId(5);
        cargo.setReceiverFullname("John");
        cargo.setDepartureBranchId(1);
        cargo.setDestinationBranchId(1);
        cargo.setPrice(120);
        cargo.setWeight(1100);
        cargo.setLength(111);
        cargo.setHeight(1);
        cargo.setWidth(2);
        cargo.setCreationDate(creationDate);
        cargo.setDeliveryDate(deliveryDate);
        cargo.setDeliveryStatus(DeliveryStatus.ON_THE_WAY);
        cargo.setInvoiceStatus(PAYED);


        BranchDAO branchDAO = new BranchDAO(dataSourceUtil);
        System.out.println(branchDAO.getAllBranches());


        UserDAO userDAO = new UserDAO(dataSourceUtil);
        //userDAO.addUser(user);
        //userDAO.deleteUserById(3);
        System.out.println(userDAO.getAllUsers());
        System.out.println(userDAO.findUserById(5));


        CargoDAO cargoDAO = new CargoDAO(dataSourceUtil);
       // cargoDAO.addCargo(cargo);
       cargoDAO.updateCargoDeliveryStatusById(DELIVERED, 1);
       cargoDAO.updateCargoInvoiceStatusById(PAYED, 1);
        System.out.println(cargoDAO.getAllCargo());
        // cargoDAO.addCargo(cargo);

    }
}
