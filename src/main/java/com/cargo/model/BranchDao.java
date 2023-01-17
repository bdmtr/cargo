package com.cargo.model;

import com.cargo.model.entity.Branch;
import com.cargo.model.enums.City;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDao {
    static final String getAllBranchesQuery = "SELECT id, city, adress FROM branch";
    static final String findBranchByIdQuery = "SELECT * FROM branch where branch.id=?";
    static final String findBranchByCityQuery = "SELECT * FROM branch where branch.city=?";
    private static BranchDao instance;

    public static synchronized BranchDao getInstance() {
        if (instance == null) instance = new BranchDao();
        return instance;
    }

    public List<Branch> getAllBranches() throws SQLException {
        List<Branch> branchList = new ArrayList<>();

        try (Connection connection = DataSourceUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllBranchesQuery);
        ) {
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("address"));
                branchList.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branchList;
    }

    public Branch getBranchById(int id) throws SQLException {
        Branch branch = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findBranchByIdQuery);
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
            e.printStackTrace();
        }
        return branch;
    }

    public Branch getBranchByCity(String city) throws SQLException {
        Branch branch = null;

        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findBranchByCityQuery);
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
            e.printStackTrace();
        }
        return branch;
    }
}