package com.cargo.model;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    static final String addUserQuery = "INSERT INTO user (username, fullname, email, password, role) VALUES(?, ?, ?, ?, ?)";
    static final String updateUserPasswordByIdQuery = "UPDATE user SET password=? WHERE user.id=?";
    static final String deleteUserByIdQuery = "DELETE FROM user WHERE user.id = ?";
    static final String findUserByIdQuery = "SELECT id, username, fullname, email, password, role FROM user where user.id = ?";
    static final String findUserByFullnameQuery = "SELECT id, username, fullname, email, password, role FROM user where user.fullname = ?";
    static final String getAllUsersQuery = "SELECT * FROM user";
    static final String findUserByUsernamePasswordQuery = "SELECT id, username, fullname, email, password, role FROM user where user.username = ? and user.password = ?";
    static final String findUserByUsernameQuery = "SELECT id, username, fullname, email, password, role FROM user where user.username = ?";

    public UserDao() {
    }

    public void addUser(User user) throws SQLException {
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFullname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateUserPasswordById(String password, int id) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateUserPasswordByIdQuery, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) throws SQLException {
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserByIdQuery);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try (
                Connection connection = DataSourceUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getAllUsersQuery);
        ) {
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
        }
        return userList;
    }

    public User findUserById(int id) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findUserByIdQuery);
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
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByFullname(String fullname) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findUserByFullnameQuery);
        ) {
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
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByUsername(String username) throws SQLException {
        User user = null;
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(findUserByUsernameQuery);
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
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByUsernamePassword(String username, String password) throws SQLException {
        ResultSet resultSet = null;
        User user = null;
        try (Connection connection = DataSourceUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findUserByUsernamePasswordQuery);
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
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }
}