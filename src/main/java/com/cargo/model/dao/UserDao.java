package com.cargo.model.dao;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.util.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    static final String ADD_USER = "INSERT INTO user (username, fullname, email, password, role, balance) VALUES(?, ?, ?, ?, ?, 1000)";
    static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE user SET password=? WHERE user.id=?";
    static final String DELETE_FROM_USER_WHERE_USER_ID = "DELETE FROM user WHERE user.id = ?";
    static final String FIND_USER_BY_ID = "SELECT id, username, fullname, email, password, role, balance  FROM user where user.id = ?";
    static final String FIND_USER_BY_FULLNAME = "SELECT id, username, fullname, email, password, role FROM user where user.fullname = ?";
    static final String GET_ALL_USERS = "SELECT * FROM user";
    static final String FIND_USER_BY_USERNAME_PASSWORD = "SELECT id, username, fullname, email, password, role, balance FROM user where user.username = ? and user.password = ?";
    static final String FIND_USER_BY_USERNAME = "SELECT id, username, fullname, email, password, role FROM user where user.username = ?";
    static final String CHANGE_BALANCE = "UPDATE user SET user.balance=? where user.id=?";


    public void addUser(User user) throws SQLException {
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFullname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Cant add user");
        }
    }

    public User findUserById(int id) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        ) {
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
                user.setBalance(resultSet.getInt("balance"));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Cant find user by id");
        }
        return user;
    }

    public User findUserByUsername(String username) throws SQLException {
        User user = null;
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_USERNAME);
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Cant find user by username");
        }
        return user;
    }

    public User findUserByUsernamePassword(String username, String password) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_USERNAME_PASSWORD);
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullname(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setBalance(resultSet.getInt("balance"));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Cant find user by username and password");
        }
        return user;
    }

    public void updateUserProfile(User user) {
        StringBuilder preQuery = new StringBuilder();
        String userId = String.valueOf(user.getId());

        if (!user.getUsername().isEmpty()) {
            preQuery.append(", user.username='").append(user.getUsername()).append("' ");
        }

        if (!user.getFullname().isEmpty()) {
            preQuery.append(", user.fullname='").append(user.getFullname()).append("' ");
        }

        if (!user.getPassword().isEmpty()) {
            preQuery.append(", user.password='").append(user.getPassword()).append("' ");
        }

        if (preQuery.length() != 0) {
            StringBuilder finalQuery = new StringBuilder();
            finalQuery.append("UPDATE user SET user.id=").append(userId).append(preQuery).append(" where user.id=").append(userId).append(";");

            try (Connection connection = DataSourceUtil.getConnection();
                 PreparedStatement pst = connection.prepareStatement(String.valueOf(finalQuery));
            ) {
                pst.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Cant update user profile");
            }
        }
    }

    public void changeBalance(int cost, int id) {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(CHANGE_BALANCE);
        ) {
            connection.setAutoCommit(false);
            pst.setInt(1, cost);
            pst.setInt(2, id);
            pst.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {

            LOGGER.error("Cant change balance");
        }
    }
}