package com.cargo.model.dao;

import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;

import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.util.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CargoDao} class provides DAO methods for working with a Cargo entity.
 * This class has the ability to get all cargo for a user by id with limit, get cargo by id, add cargo, update cargo,
 * sort cargo by city, sort for manager, and change invoice status.
 * It uses singleton design pattern to ensure that there is only one instance of the class created.
 */
public class CargoDao {
    private static final Logger LOGGER = Logger.getLogger(CargoDao.class);
    private static CargoDao instance;
    private int noOfRecords;

    /**
     * Gets the instance of the class.
     * If the instance is not yet created, it will create a new instance.
     *
     * @return instance of the class
     */
    public static synchronized CargoDao getInstance() {
        if (instance == null) instance = new CargoDao();
        return instance;
    }

    static final String GET_ALL_CARGO_WITH_LIMIT_BY_USER_ID = "select SQL_CALC_FOUND_ROWS * from cargo where cargo.user_id=";
    static final String FIND_CARGO_BY_ID = "SELECT * FROM cargo where cargo.id = ?";
    static final String ADD_CARGO = "INSERT INTO cargo (type, user_id, receiver_fullname, departure_branch_id,destination_branch_id, price, weight, " +
            "length, height, width, creation_date, delivery_date, delivery_status, invoice_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    static final String SORT_CARGO_BY_CITY = "select SQL_CALC_FOUND_ROWS departure_branch_id, destination_branch_id, " +
            "delivery_date, delivery_status from cargo where delivery_status='TRANSIT' ";
    static final String SORT_FOR_MANAGER = "select SQL_CALC_FOUND_ROWS * from cargo where id>0 ";
    static final String CHANGE_INVOICE_STATUS = "UPDATE cargo SET cargo.invoice_status='PAYED' where cargo.id=?";

    /**
     * Gets a list of all cargos for a user by id with a limit on the number of records returned.
     *
     * @param id          user id
     * @param offset      number of records to skip
     * @param noOfRecords maximum number of records to retrieve
     * @return a list of cargos
     */
    public List<Cargo> getAllCargoForUserByIdWithLimit(int id, int offset, int noOfRecords) {
        List<Cargo> list = new ArrayList<Cargo>();
        Cargo cargo = null;

        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(GET_ALL_CARGO_WITH_LIMIT_BY_USER_ID + id + " LIMIT " + offset + ", " + noOfRecords);

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUser(UserDao.getInstance().findUserById(rs.getInt("user_id")));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(rs.getInt("departure_branch_id")));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(rs.getInt("destination_branch_id")));
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
            LOGGER.error("Cant get all cargo for user by id with limit");
        }

        return list;
    }

    /**
     * Gets a cargo by id.
     *
     * @param id cargo id
     * @return a cargo with the given id
     * @throws SQLException if there's an error in executing the query
     */
    public Cargo getCargoById(int id) throws SQLException {
        Cargo cargo = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARGO_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setType(rs.getString("type"));
                cargo.setUser(UserDao.getInstance().findUserById(rs.getInt("user_id")));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(rs.getInt("departure_branch_id")));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(rs.getInt("destination_branch_id")));
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
            LOGGER.error("Cant get cargo by id");
        }
        return cargo;
    }

    /**
     * Adds a new cargo to the database.
     *
     * @param cargo the cargo to be added
     * @throws SQLException if there's an error in executing the query
     */
    public void addCargo(Cargo cargo) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CARGO, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, cargo.getType());
            preparedStatement.setInt(2, cargo.getUser().getId());
            preparedStatement.setString(3, cargo.getReceiverFullname());
            preparedStatement.setInt(4, cargo.getDepartureBranch().getId());
            preparedStatement.setInt(5, cargo.getDestinationBranch().getId());
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
            LOGGER.error("Cant add cargo");
        }
    }

    /**
     * Updates an existing cargo in the database.
     *
     * @param cargo the cargo to be updated
     */
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
                LOGGER.error("Cant update cargo profile");
            }
        }
    }

    /**
     * Changes the status of an invoice with the given cargo id.
     *
     * @param id the id of the cargo to change the status for
     */
    public void changeInvoiceStatus(int id) {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(CHANGE_INVOICE_STATUS);
        ) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cant change invoice stsus");
        }
    }

    /**
     * Sorts the list of {@link Cargo} objects based on their delivery city and destination date.
     *
     * @param offset      The starting index of the list to retrieve.
     * @param noOfRecords The number of records to retrieve.
     * @param branchCity  The city of the delivery branch to filter the list by.
     * @param order       The order to sort the list by (either "ASC" or "DESC").
     * @return A list of {@link Cargo} objects sorted by their delivery city and date.
     * @throws SQLException If there is a problem executing the SQL query.
     */
    public List<Cargo> sortByCityDate(int offset, int noOfRecords, String branchCity, String order) throws SQLException {
        StringBuilder preQuery = new StringBuilder(SORT_CARGO_BY_CITY);
        String id;

        BranchDao branchDao = new BranchDao();
        Branch branch = branchDao.getBranchByCity(branchCity);

        if (branchCity == null) {
            preQuery.append(" AND destination_branch_id>0");
        }

        if (branch != null) {
            id = String.valueOf(branch.getId());
            preQuery.append(" AND destination_branch_id=").append(id);
        }

        if (order != null && !order.isEmpty()) {
            preQuery.append(" ORDER BY delivery_date ").append(order).append(" LIMIT ").append(offset).append(", ").append(noOfRecords);
        } else preQuery.append(" ORDER BY delivery_date ASC LIMIT ").append(offset).append(", ").append(noOfRecords);

        List<Cargo> list = new ArrayList<>();
        Cargo cargo = null;
        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(String.valueOf(preQuery));

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(rs.getInt("departure_branch_id")));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(rs.getInt("destination_branch_id")));
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
            LOGGER.error("Cant sort by city and date");
        }
        return list;
    }

    /**
     * Sorts the list of cargo based on the city and date specified for manager.
     *
     * @param offset          the starting point of the records to retrieve
     * @param noOfRecords     the number of records to retrieve
     * @param destinationBrId the id of the destination branch
     * @param date            the date of the delivery
     * @param order           the order in which the records should be sorted (ASC/DESC)
     * @return a list of cargo sorted by the city and date specified by the manager
     * @throws SQLException if an error occurs while sorting the cargo
     */
    public List<Cargo> sortByCityDateManager(int offset, int noOfRecords, String destinationBrId, String date, String order) throws SQLException {
        StringBuilder preQuery = new StringBuilder(SORT_FOR_MANAGER);


        if (destinationBrId != null && !destinationBrId.isEmpty()) {
            if (destinationBrId.equals("0")) {
                preQuery.append(" AND destination_branch_id>0");
            } else preQuery.append(" AND destination_branch_id=").append(destinationBrId);
        }

        if (date != null) {
            preQuery.append(" AND delivery_date like '" + date + "%' ");
        }

        if (order != null && !order.isEmpty()) {
            preQuery.append(" ORDER BY delivery_date ").append(order).append(" LIMIT ").append(offset).append(", ").append(noOfRecords);
        } else preQuery.append(" ORDER BY delivery_date ASC LIMIT ").append(offset).append(", ").append(noOfRecords);

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
                cargo.setUser(UserDao.getInstance().findUserById(rs.getInt("user_id")));
                cargo.setReceiverFullname(rs.getString("receiver_fullname"));
                cargo.setDepartureBranch(BranchDao.getInstance().getBranchById(rs.getInt("departure_branch_id")));
                cargo.setDestinationBranch(BranchDao.getInstance().getBranchById(rs.getInt("destination_branch_id")));
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
            LOGGER.error("Cant sort cargo for manager");
        }
        return list;
    }

    /**
     * Gets the total number of records.
     *
     * @return the total number of records.
     */
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
