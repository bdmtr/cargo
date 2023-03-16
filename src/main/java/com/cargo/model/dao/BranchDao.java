package com.cargo.model.dao;

import com.cargo.exceptions.DaoException;
import com.cargo.model.entity.Branch;
import com.cargo.model.enums.City;
import com.cargo.util.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDao {
    private static final Logger LOGGER = Logger.getLogger(BranchDao.class);

    static final String GET_ALL_BRANCHES = "SELECT id, city, address FROM branch";
    static final String FIND_BRANCH_BY_ID = "SELECT * FROM branch where branch.id=?";
    static final String FIND_BRANCH_BY_CITY = "SELECT * FROM branch where branch.city=?";
    private static BranchDao instance;

    /**
     * Gets the instance of the class.
     * If the instance is not yet created, it will create a new instance.
     *
     * @return instance of the class
     */
    public static synchronized BranchDao getInstance() {
        if (instance == null) instance = new BranchDao();
        return instance;
    }

    public List<Branch> getAllBranches() throws DaoException {
        List<Branch> branchList = new ArrayList<>();

        try (Connection connection = DataSourceUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_BRANCHES);
        ) {
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("address"));
                branchList.add(branch);
            }
        } catch (SQLException e) {
            LOGGER.error("Cant get all branches", e);
            throw new DaoException(e.getMessage());
        }
        return branchList;
    }

    public Branch getBranchById(int id) throws DaoException {
        Branch branch = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BRANCH_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            LOGGER.error("Cant get branch by id", e);
            throw new DaoException(e.getMessage());
        }
        return branch;
    }

    public Branch getBranchByCity(String city) throws DaoException {
        Branch branch = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BRANCH_BY_CITY);
        ) {
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            LOGGER.error("Cant get branch by city", e);
            throw new DaoException(e.getMessage());
        }
        return branch;
    }
}