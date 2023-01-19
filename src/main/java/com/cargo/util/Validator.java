package com.cargo.util;

import com.cargo.model.BranchDao;
import com.cargo.model.UserDao;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Validator {
    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{4,}";
    private static final String EMAIL_REGEX = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String NAME_REGEX = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\\- ]{1,20}";

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

        if (email.isEmpty() || !email.matches(EMAIL_REGEX)) {
            return true;
        }
        if (!username.matches(NAME_REGEX)) {
            return true;
        }

        if(password.matches(PASSWORD_REGEX)) {
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
