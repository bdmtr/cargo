package com.cargo.dao;

import com.cargo.dao.entity.User;
import com.cargo.dao.enums.Role;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
private final DataSourceUtil dataSourceUtil;


    static final String addUserQuery = "INSERT INTO user (username, fullname, email, password, role) VALUES(?, ?, ?, ?, ?)";
    //static final String updateUserQuery = "UPDATE user SET username=?, fullname=?, email=?, password=?, role=? WHERE user.id=?";
    static final String updateUserPasswordByIdQuery = "UPDATE user password=? WHERE user.id=?";
    static final String deleteUserByIdQuery = "DELETE FROM user WHERE user.id = ?";
    static final String findUserByIdQuery = "SELECT id, username, fullname, email, password, role FROM user where user.id = ?";
    static final String findUserByFullnameQuery = "SELECT id, username, fullname, email, password, role FROM user where user.fullname = ?";
    static final String getAllUsersQuery = "SELECT * FROM user";

    public UserDAO(DataSourceUtil dataSourceUtil) {
        this.dataSourceUtil = dataSourceUtil;
    }

    //public boolean isExist(User user)

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(addUserQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFullname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().toString());
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

    public void setUpdateUserPasswordById(String password, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateUserPasswordByIdQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, password);
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

    public void deleteUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSourceUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(deleteUserByIdQuery);
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

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSourceUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllUsersQuery);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                userList.add(user);
            }
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
        return userList;
    }

    public User findUserById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(findUserByIdQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert resultSet != null;
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    public User findUserByFullname(String fullname) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSourceUtil.getConnection();
            preparedStatement = connection.prepareStatement(findUserByFullnameQuery);
            preparedStatement.setString(1, fullname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert resultSet != null;
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }
}