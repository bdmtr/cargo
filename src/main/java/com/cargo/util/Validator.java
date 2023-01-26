package com.cargo.util;

import com.cargo.model.dao.BranchDao;
import com.cargo.model.dao.UserDao;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.UserService;

import java.sql.SQLException;

public class Validator {

    private static final String FULLNAME_REGEX = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\\- ]{1,20}";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$";


    public static boolean isIncorrectLoginInfo(String username) {
        return username == null || username.isEmpty();
    }

    public static boolean isIncorrectRegisterInfo(String username, String fullName, String email, String password) throws SQLException {
        UserService userService = new UserService(UserDao.getInstance());

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
        if (!fullName.matches(FULLNAME_REGEX)) {
            return true;
        }

        if (!password.matches(PASSWORD_REGEX)) {
            return true;
        }

        return userService.findUserByUsername(username) != null;
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
        if (Integer.parseInt(weight) < 1 || Integer.parseInt(height) < 1 || Integer.parseInt(length) < 1 || Integer.parseInt(width) < 1) {
            return true;
        }
        if (Integer.parseInt(weight) > 1000 || Integer.parseInt(height) > 1000 || Integer.parseInt(length) > 1000 || Integer.parseInt(width) > 1000) {
            return true;
        }

        if (departureBranchId == 0 || destinationBranchId == 0) {
            return true;
        }

        BranchService branchService = new BranchService(BranchDao.getInstance());

        return branchService.getBranchById(departureBranchId) == null || branchService.getBranchById(destinationBranchId) == null;
    }

    public static boolean isIncorrectCalculateInfo(int departureBranchId, int destinationBranchId, String weight, String height, String length, String width) throws SQLException {

        if (Integer.parseInt(weight) < 1 || Integer.parseInt(height) < 1 || Integer.parseInt(length) < 1 || Integer.parseInt(width) < 1) {
            return true;
        }
        if (Integer.parseInt(weight) > 1000 || Integer.parseInt(height) > 1000 || Integer.parseInt(length) > 1000 || Integer.parseInt(width) > 1000) {
            return true;
        }

        if (departureBranchId == 0 || destinationBranchId == 0) {
            return true;
        }

        BranchService branchService = new BranchService(BranchDao.getInstance());

        return branchService.getBranchById(departureBranchId) == null || branchService.getBranchById(destinationBranchId) == null;
    }
}
