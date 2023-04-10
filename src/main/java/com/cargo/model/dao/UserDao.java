package com.cargo.model.dao;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.util.DataSourceUtil;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * The {@code UserDao} class provides DAO methods for working with a User entity.
 * It uses singleton design pattern to ensure that there is only one instance of the class created.
 */
public class UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    /**
     * Gets the instance of the class.
     * If the instance is not yet created, it will create a new instance.
     *
     * @return instance of the class
     */
    public static synchronized UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    static final String ADD_USER = "INSERT INTO user (username, fullname, email, password, role, balance) VALUES(?, ?, ?, ?, ?, 1000)";
    static final String FIND_USER_BY_ID = "SELECT id, username, fullname, email, password, role, balance  FROM user where user.id = ?";
    static final String FIND_USER_BY_USERNAME_PASSWORD = "SELECT id, username, fullname, email, password, role, balance FROM user where user.username = ? and user.password = ?";
    static final String FIND_USER_BY_USERNAME = "SELECT id, username, fullname, email, password, role FROM user where user.username = ?";
    static final String CHANGE_BALANCE = "UPDATE user SET user.balance=? where user.id=?";

    /**
     * Adds a new User to the database.
     *
     * @param user the User to add
     * @throws SQLException if there's an error in executing the query
     */
    public void addUser(User user){
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

    /**
     * Finds a User by its id.
     *
     * @param id the id of the User to find
     * @return the User with the specified id
     * @throws SQLException if there's an error in executing the query
     */
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

    /**
     * Finds a User in the database by its username.
     *
     * @param username the username of the User to find
     * @return the User with the specified username or null if there is no User with the specified username
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public User findUserByUsername(String username) {
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

    /**
     * Finds a User in the database by its username and password.
     *
     * @param username the username of the User to find
     * @param password the password of the User to find
     * @return the User with the specified username and password or null if there is no User with the specified username and password
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public User findUserByUsernamePassword(String username, String password) {
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

    /**
     * This method updates a user profile with the provided user object.
     * The following user fields can be updated: username, fullname, and password.
     *
     * @param user the user object to update the profile with
     */
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

    /**
     * Changes the balance of a User in the database.
     *
     * @param cost the new balance of the User
     * @param id   the id of the User to change the balance of
     */
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