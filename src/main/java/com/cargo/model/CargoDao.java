package com.cargo.model;


import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;

import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDao {
    private int noOfRecords;

    static final String getAllCargoQuery = "SELECT id, type, user_id, receiver_fullname, departure_branch_id," +
            " destination_branch_id, price, weight, length, height, width, creation_date, delivery_date, delivery_status, invoice_status FROM cargo";


    static final String getAllCargosForUserQuery = "SELECT id, type, user_id, receiver_fullname, departure_branch_id," +
            " destination_branch_id, price, weight, length, height, width, creation_date, delivery_date, delivery_status, invoice_status FROM cargo " +
            "WHERE user_id = ? ORDER BY creation_date";

    static final String getAllForIdWithLimitQuery = "select SQL_CALC_FOUND_ROWS * from cargo where cargo.user_id=";

    static final String getAllWithLimitQuery = "select SQL_CALC_FOUND_ROWS * from cargo";

    static final String getAllGuestWithLimitQuery = "select SQL_CALC_FOUND_ROWS departure_branch_id, destination_branch_id, delivery_date, " +
            "delivery_status from cargo where delivery_status='TRANSIT' ";

    static final String findCargoByIdQuery = "SELECT * FROM cargo where cargo.id = ?";

    static final String findCargoByUserIdQuery = "SELECT  id, type, user_id, receiver_fullname, departure_branch_id, destination_branch_id," +
            " price, weight, length, height, width, creation_date, delivery_date, delivery_status, invoice_status FROM cargo where cargo.user_id = ?";

    static final String addCargoQuery = "INSERT INTO cargo (type, user_id, receiver_fullname, departure_branch_id,destination_branch_id, price, weight, " +
            "length, height, width, creation_date, delivery_date, delivery_status, invoice_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    static final String updateCargoQuery = "UPDATE cargo SET type=?, user_id=?, receiver_fullname=?, departure_branch_id=?," +
            "destination_branch_id=?, price=?, weight=?, length=?, height=?, width=?, creation_date=?," +
            "delivery_date=?, delivery_status=?, invoice_status=? WHERE cargo.id=?";

    static final String updateCargoInvoiceStatusByIdQuery = "UPDATE cargo SET invoice_status=? WHERE cargo.id=?";

    static final String updateCargoDeliveryStatusByIdQuery = "UPDATE cargo SET delivery_status=? WHERE cargo.id=?";

    static final String deleteCargoByIdQuery = "DELETE FROM cargo WHERE cargo.id=?";

    static final String deleteCargoByUserIdQuery = "DELETE FROM cargo WHERE cargo.user_id=?";

    static final String sortByCityQuery = "select SQL_CALC_FOUND_ROWS departure_branch_id, destination_branch_id, " +
            "delivery_date, delivery_status from cargo where delivery_status='TRANSIT' ";

    static final String sortByCityManagerQuery = "select SQL_CALC_FOUND_ROWS * from cargo where id>0 ";

    public List<Cargo> getAllCargo() throws SQLException {
        List<Cargo> cargoList = new ArrayList<>();
        try (Connection connection = DataSourceUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllCargoQuery);
        ) {
            while (resultSet.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setType(resultSet.getString("type"));
                cargo.setUserId(resultSet.getInt("user_id"));
                cargo.setReceiverFullname(resultSet.getString("receiver_fullname"));
                cargo.setDepartureBranchId(resultSet.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(resultSet.getInt("destination_branch_id"));
                cargo.setPrice(resultSet.getInt("price"));
                cargo.setWeight(resultSet.getInt("weight"));
                cargo.setLength(resultSet.getInt("length"));
                cargo.setHeight(resultSet.getInt("height"));
                cargo.setWidth(resultSet.getInt("width"));
                cargo.setCreationDate(resultSet.getTimestamp("creation_date"));
                cargo.setDeliveryDate(resultSet.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(resultSet.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(resultSet.getString("invoice_status")));

                cargoList.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cargoList;
    }

    public List<Cargo> getAllCargoForUserById(int id) throws SQLException {
        List<Cargo> list = new ArrayList<>();
        Cargo cargo = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllCargosForUserQuery);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setType(resultSet.getString("type"));
                cargo.setUserId(resultSet.getInt("user_id"));
                cargo.setUser(UserDao.getInstance().findUserById(cargo.getUserId()));
                cargo.setReceiverFullname(resultSet.getString("receiver_fullname"));
                cargo.setDepartureBranchId(resultSet.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(resultSet.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getUserId()));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getUserId()));
                cargo.setPrice(resultSet.getInt("price"));
                cargo.setWeight(resultSet.getInt("weight"));
                cargo.setLength(resultSet.getInt("length"));
                cargo.setHeight(resultSet.getInt("height"));
                cargo.setWidth(resultSet.getInt("width"));
                cargo.setCreationDate(resultSet.getTimestamp("creation_date"));
                cargo.setDeliveryDate(resultSet.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(resultSet.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(resultSet.getString("invoice_status")));

                list.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Cargo> getAllCargoForUserByIdWithLimit(int id, int offset, int noOfRecords) throws SQLException {
        List<Cargo> list = new ArrayList<Cargo>();
        Cargo cargo = null;

        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(getAllForIdWithLimitQuery + id + " LIMIT " + offset + ", " + noOfRecords);

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUserId(rs.getInt("user_id"));
                cargo.setUser(UserDao.getInstance().findUserById(cargo.getUserId()));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setPrice(rs.getInt("price"));
                cargo.setWeight(rs.getInt("weight"));
                cargo.setLength(rs.getInt("length"));
                cargo.setHeight(rs.getInt("height"));
                cargo.setWidth(rs.getInt("width"));
                cargo.setCreationDate(rs.getTimestamp("creation_date"));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(rs.getString("invoice_status")));

                list.add(cargo);
            }

            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Cargo> getAllCargoWithLimit(int offset, int noOfRecords) throws SQLException {
        List<Cargo> list = new ArrayList<Cargo>();
        Cargo cargo = null;

        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(getAllWithLimitQuery + " LIMIT " + offset + ", " + noOfRecords);

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUserId(rs.getInt("user_id"));
                cargo.setUser(UserDao.getInstance().findUserById(cargo.getUserId()));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setPrice(rs.getInt("price"));
                cargo.setWeight(rs.getInt("weight"));
                cargo.setLength(rs.getInt("length"));
                cargo.setHeight(rs.getInt("height"));
                cargo.setWidth(rs.getInt("width"));
                cargo.setCreationDate(rs.getTimestamp("creation_date"));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(rs.getString("invoice_status")));

                list.add(cargo);
            }

            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Cargo> getAllGuestCargoWithLimit(int offset, int noOfRecords) throws SQLException {
        List<Cargo> list = new ArrayList<Cargo>();
        Cargo cargo = null;

        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(getAllGuestWithLimitQuery + " LIMIT " + offset + ", " + noOfRecords);

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));

                list.add(cargo);
            }

            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Cargo getCargoById(int id) throws SQLException {
        Cargo cargo = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findCargoByIdQuery);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUserId(rs.getInt("user_id"));
                cargo.setUser(UserDao.getInstance().findUserById(cargo.getUserId()));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setPrice(rs.getInt("price"));
                cargo.setWeight(rs.getInt("weight"));
                cargo.setLength(rs.getInt("length"));
                cargo.setHeight(rs.getInt("height"));
                cargo.setWidth(rs.getInt("width"));
                cargo.setCreationDate(rs.getTimestamp("creation_date"));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(rs.getString("invoice_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargo;
    }

    public void addCargo(Cargo cargo) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addCargoQuery, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, cargo.getType());
            preparedStatement.setInt(2, cargo.getUserId());
            preparedStatement.setString(3, cargo.getReceiverFullname());
            preparedStatement.setInt(4, cargo.getDepartureBranchId());
            preparedStatement.setInt(5, cargo.getDestinationBranchId());
            preparedStatement.setInt(6, cargo.getPrice());
            preparedStatement.setInt(7, cargo.getWeight());
            preparedStatement.setInt(8, cargo.getLength());
            preparedStatement.setInt(9, cargo.getHeight());
            preparedStatement.setInt(10, cargo.getWidth());
            preparedStatement.setTimestamp(11, cargo.getCreationDate());
            preparedStatement.setTimestamp(12, cargo.getDeliveryDate());
            preparedStatement.setString(13, cargo.getDeliveryStatus().toString());
            preparedStatement.setString(14, cargo.getInvoiceStatus().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCargoById(int id) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteCargoByIdQuery);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCargoByUserId(int id) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteCargoByUserIdQuery);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCargoProfile(Cargo cargo) {
        StringBuilder preQuery = new StringBuilder();
        String cargoId = String.valueOf(cargo.getId());
        String deliveryStatus = cargo.getDeliveryStatus().toString();
        String invoiceStatus = cargo.getInvoiceStatus().toString();

        if (!cargo.getReceiverFullname().isEmpty()) {
            preQuery.append(", cargo.receiver_fullname='").append(cargo.getReceiverFullname()).append("' ");
        }

        if (!deliveryStatus.isEmpty()) {
            preQuery.append(", cargo.delivery_status='").append(cargo.getDeliveryStatus()).append("' ");
        }

        if (!invoiceStatus.isEmpty()) {
            preQuery.append(", cargo.invoice_status='").append(cargo.getInvoiceStatus()).append("' ");
        }
        if (preQuery.length() != 0) {
            try (Connection connection = DataSourceUtil.getConnection();
                 PreparedStatement pst = connection.prepareStatement(String.valueOf("UPDATE cargo SET cargo.id=" + cargoId + preQuery + " where cargo.id=" + cargoId + ";"));
            ) {
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cargo> sortByCityDate(int offset, int noOfRecords, String branchCity, String order) throws SQLException {
        StringBuilder preQuery = new StringBuilder(sortByCityQuery);
        String id;

        BranchDao branchDao = new BranchDao();
        Branch branch = branchDao.getBranchByCity(branchCity);

        if (branch != null) {
            id = String.valueOf(branch.getId());
            preQuery.append(" AND destination_branch_id=").append(id);
        }

        if (order != null && !order.isEmpty()) {
            preQuery.append(" ORDER BY delivery_date ").append(order).append(" LIMIT ").append(offset).append(", ").append(noOfRecords);
        } else preQuery.append(" ORDER BY delivery_date ASC LIMIT ").append(offset).append(", ").append(noOfRecords);

        System.out.println(preQuery);

        List<Cargo> list = new ArrayList<>();
        Cargo cargo = null;
        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(String.valueOf(preQuery));

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));

                list.add(cargo);
            }

            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Cargo> sortByCityDateManager(int offset, int noOfRecords, String departmentBrId, String destinationBrId, String date, String order) throws SQLException {
        StringBuilder preQuery = new StringBuilder(sortByCityManagerQuery);

        if (departmentBrId != null && !departmentBrId.isEmpty()) {
            preQuery.append(" AND departure_branch_id=").append(departmentBrId);
        }

        if (destinationBrId != null && !destinationBrId.isEmpty()) {
            preQuery.append(" AND destination_branch_id=").append(destinationBrId);
        }

        if (date != null) {
            preQuery.append(" AND delivery_date like '%" + date + "%' ");
        }

        if (order != null && !order.isEmpty()) {
            preQuery.append(" ORDER BY delivery_date ").append(order).append(" LIMIT ").append(offset).append(", ").append(noOfRecords);
        } else preQuery.append(" ORDER BY delivery_date ASC LIMIT ").append(offset).append(", ").append(noOfRecords);

        System.out.println(preQuery);

        List<Cargo> list = new ArrayList<>();
        Cargo cargo = null;
        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(String.valueOf(preQuery));

            while (rs.next()) {
                cargo = new Cargo();

                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUserId(rs.getInt("user_id"));
                cargo.setUser(UserDao.getInstance().findUserById(cargo.getUserId()));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranchId(rs.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(rs.getInt("destination_branch_id"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(cargo.getDepartureBranchId()));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(cargo.getDestinationBranchId()));
                cargo.setPrice(rs.getInt("price"));
                cargo.setWeight(rs.getInt("weight"));
                cargo.setLength(rs.getInt("length"));
                cargo.setHeight(rs.getInt("height"));
                cargo.setWidth(rs.getInt("width"));
                cargo.setCreationDate(rs.getTimestamp("creation_date"));
                cargo.setDeliveryDate(rs.getTimestamp("delivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(rs.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(rs.getString("invoice_status")));

                list.add(cargo);
            }

            rs.close();
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");

            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public int getNoOfRecords() {
        return noOfRecords;
    }
}