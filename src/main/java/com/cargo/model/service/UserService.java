package com.cargo.model.service;

import com.cargo.model.dao.UserDao;
import com.cargo.model.entity.User;
import com.cargo.util.PasswordHasher;

import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) throws SQLException {
        String encryptedPassword = PasswordHasher.hash(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.addUser(user);
    }

    public User findUserById(int id) throws SQLException {
        return userDao.findUserById(id);
    }

    public User findUserByUsername(String username) throws SQLException {
        return userDao.findUserByUsername(username);
    }

    public User findUserByUsernamePassword(String username, String password) throws SQLException {
        User user = userDao.findUserByUsername(username);
        if (PasswordHasher.verify(password, user.getPassword())) {
            return userDao.findUserByUsernamePassword(username, user.getPassword());
        } else {
            return null;
        }
    }

    public void updateUserProfile(User user) {
        String encryptedPassword = PasswordHasher.hash(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.updateUserProfile(user);
    }

    public void changeBalance(int cost, int id) {
        userDao.changeBalance(cost, id);
    }
}