package com.cargo.dao;

import com.cargo.dao.entity.Branch;
import com.cargo.dao.enums.City;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO {
    private final DataSourceUtil dataSourceUtil;


    static final String getAllBranchesQuery = "SELECT id, city, adress FROM branch";
    static final String findBranchByIdQuery = "SELECT id, city, adress FROM branch where branch.id = ?";

    public BranchDAO(DataSourceUtil dataSourceUtil) {
      this.dataSourceUtil = dataSourceUtil;
 }




    public List<Branch> getAllBranches() throws SQLException {
        List<Branch> branchList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection =  dataSourceUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllBranchesQuery);
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("adress"));
                branchList.add(branch);
            }
        } finally {
            assert resultSet != null;
            resultSet.close();
            statement.close();
            connection.close();
        }
        return branchList;
    }

    public Branch getBranchById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Branch branch = null;

        try {
            connection =  dataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(findBranchByIdQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                branch = new Branch();
                branch.setId(resultSet.getInt("id"));
                branch.setCity(City.valueOf(resultSet.getString("city")));
                branch.setAddress(resultSet.getString("adress"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert resultSet != null;
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return branch;
    }
}