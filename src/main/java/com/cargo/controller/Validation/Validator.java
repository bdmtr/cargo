package com.cargo.controller.Validation;

import com.cargo.model.BranchDao;
import com.cargo.model.UserDao;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Validator {

    public static boolean isIncorrectLoginInfo(HttpServletRequest request, String username) {
        return username == null || username.isEmpty();
    }

    public static boolean isIncorrectRegisterInfo(HttpServletRequest request, String username, String fullName, String email, String password) throws SQLException {
        UserDao userDao = new UserDao();
        if (username == null || username.isEmpty()) {
            return true;
        }

        if (username.length() > 15) {
            return true;
        }

        if (fullName == null || fullName.isEmpty()) {
            return true;
        }

        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return true;
        }
        if (password.length() < 4 || password.length() > 20) {
            return true;
        }

        if (userDao.findUserByUsername(username) != null) {
            return true;
        }
        return userDao.findUserByUsername(username).getEmail().equals(email);
    }

    public static boolean isIncorrectEditInfo(String username, String fullName, String password) throws SQLException {
        UserDao userDao = new UserDao();
        if (username == null || username.isEmpty()) {
            return true;
        }

        if (username.length() > 15) {
            return true;
        }

        if (fullName == null || fullName.isEmpty()) {
            return true;
        }

        if (password.length() < 4 || password.length() > 20) {
            return true;
        }

        return userDao.findUserByUsername(username) != null;
    }


    public static boolean isIncorrectCargoInfo(String type, String receiverFullname, int departureBranchId,
                                               int destinationBranchId, String weight, String height, String length, String width)
            throws SQLException {
        if (type == null) {
            return true;
        }
        if (receiverFullname.isEmpty()) {
            return true;
        }
        if (Integer.parseInt(weight) < 1) {
            return true;
        }
        if (Integer.parseInt(height) < 1) {
            return true;
        }
        if (Integer.parseInt(length) < 1) {
            return true;
        }
        if (Integer.parseInt(width) < 1) {
            return true;
        }

        if (departureBranchId == 0 || destinationBranchId == 0) {
            return true;
        }

        BranchDao branchDao = new BranchDao();

        return branchDao.getBranchById(departureBranchId) == null || branchDao.getBranchById(destinationBranchId) == null;
    }

    public static boolean isIncorrectCalculateInfo(int departureBranchId, int destinationBranchId, String weight, String height, String length, String width) throws SQLException {

        if (Integer.parseInt(weight) < 1) {
            return true;
        }
        if (Integer.parseInt(height) < 1) {
            return true;
        }
        if (Integer.parseInt(length) < 1) {
            return true;
        }
        if (Integer.parseInt(width) < 1) {
            return true;
        }

        if (departureBranchId == 0 || destinationBranchId == 0) {
            return true;
        }

        BranchDao branchDao = new BranchDao();

        return branchDao.getBranchById(departureBranchId) == null || branchDao.getBranchById(destinationBranchId) == null;
    }
}
