package com.cargo.dao;


import com.cargo.dao.entity.Cargo;

import com.cargo.dao.enums.DeliveryStatus;
import com.cargo.dao.enums.InvoiceStatus;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {
    private final DataSourceUtil dataSourceUtil;


    static final String getAllCargoQuery = "SELECT id, type, user_id, receiver_fullname, departure_branch_id," +
            " destination_branch_id, price, weight, length, height, width, creation_date, delivery_date, delivery_status, invoice_status FROM cargo";
    static final String findCargoByIdQuery = "SELECT id, type, user_id, receiver_fullname, departure_branch_id, destination_branch_id," +
            " price, weight, length, height, width, creation_date, delivery_date, delivery_status, invoice_status FROM cargo where cargo.id = ?";
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

    public CargoDAO(DataSourceUtil dataSourceUtil) {
        this.dataSourceUtil = dataSourceUtil;
    }


    public List<Cargo> getAllCargo() throws SQLException {
        List<Cargo> cargoList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSourceUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllCargoQuery);
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
        } finally {
            assert resultSet != null;
            resultSet.close();
            statement.close();
            connection.close();
        }
        return cargoList;
    }

    public Cargo getCargoById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cargo cargo = null;

        try {
            connection = dataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(findCargoByIdQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cargo = new Cargo();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert resultSet != null;
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return cargo;
    }

    public Cargo getCargoByUserId(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cargo cargo = null;

        try {
            connection = dataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(findCargoByUserIdQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setType(resultSet.getString("type"));
                cargo.setUserId(resultSet.getInt("user_id"));
                cargo.setReceiverFullname(resultSet.getString("recipient_fullname"));
                cargo.setDepartureBranchId(resultSet.getInt("departure_branch_id"));
                cargo.setDestinationBranchId(resultSet.getInt("destination_branch_id"));
                cargo.setPrice(resultSet.getInt("price"));
                cargo.setWeight(resultSet.getInt("weight"));
                cargo.setLength(resultSet.getInt("length"));
                cargo.setHeight(resultSet.getInt("height"));
                cargo.setWidth(resultSet.getInt("width"));
                cargo.setCreationDate(resultSet.getTimestamp("creation_date"));
                cargo.setDeliveryDate(resultSet.getTimestamp("deluivery_date"));
                cargo.setDeliveryStatus(DeliveryStatus.valueOf(resultSet.getString("delivery_status")));
                cargo.setInvoiceStatus(InvoiceStatus.valueOf(resultSet.getString("invoice_status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert resultSet != null;
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return cargo;
    }

    public void addCargo(Cargo cargo) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(addCargoQuery, Statement.RETURN_GENERATED_KEYS);
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
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

/*    public void updateCargo(Cargo cargo) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateCargoQuery, Statement.RETURN_GENERATED_KEYS);

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
            preparedStatement.setInt(15, cargo.getId());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }*/

    public void updateCargoInvoiceStatusById(InvoiceStatus invoiceStatus, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateCargoInvoiceStatusByIdQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, invoiceStatus.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

    public void updateCargoDeliveryStatusById(DeliveryStatus deliveryStatus, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateCargoDeliveryStatusByIdQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, deliveryStatus.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

    public void deleteCargoById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(deleteCargoByIdQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

    public void deleteCargoByUserId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(deleteCargoByUserIdQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }
}
