package com.cargo.model.service;

import com.cargo.model.dao.UserDao;
import com.cargo.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    public void updateUserPasswordById(String password, int id) {
        userDao.updateUserPasswordById(password, id);
    }

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User findUserById(int id) throws SQLException {
        return userDao.findUserById(id);
    }

    public User findUserByFullname(String fullname) {
        return userDao.findUserByFullname(fullname);
    }

    public User findUserByUsername(String username) throws SQLException {
        return userDao.findUserByUsername(username);
    }

    public User findUserByUsernamePassword(String username, String password) throws SQLException {
        return userDao.findUserByUsernamePassword(username, password);
    }

    public void updateUserProfile(User user) {
        userDao.updateUserProfile(user);
    }

    public void changeBalance(int cost, int id) {
        userDao.changeBalance(cost, id);
    }
}
