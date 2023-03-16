package com.cargo.util;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.BranchDao;
import com.cargo.model.dao.UserDao;
import com.cargo.model.service.BranchService;
import com.cargo.model.service.UserService;

import java.sql.SQLException;

/**
 * This class validate information for user registration, login, cargo creation, and cargo cost calculation.
 */
public class Validator {

    private static final String FULLNAME_REGEX = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\\- ]{1,20}";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$";

    /**
     * Checks if the given username is empty.
     *
     * @param username The username to check.
     * @return {@code true} if the username is {@code null} or empty, {@code false} otherwise.
     */
    public static boolean isIncorrectLoginInfo(String username) {
        return username == null || username.isEmpty();
    }

    /**
     * Checks if the given information is incorrect for user registration purposes.
     *
     * @param username The username to check.
     * @param fullName The full name to check.
     * @param email    The email to check.
     * @param password The password to check.
     * @return {@code true} if the information is incorrect, {@code false} otherwise.
     * @throws SQLException If a database error occurs while checking the information.
     */
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

    /*    if (!password.matches(PASSWORD_REGEX)) {
            return true;
        }*/

        return userService.findUserByUsername(username) != null;
    }

    /**
     * Determines if the cargo information is incorrect.
     *
     * @param type                The type of the cargo.
     * @param receiverFullname    The full name of the receiver.
     * @param departureBranchId   The ID of the departure branch.
     * @param destinationBranchId The ID of the destination branch.
     * @param weight              The weight of the cargo.
     * @param height              The height of the cargo.
     * @param length              The length of the cargo.
     * @param width               The width of the cargo.
     * @return <code>true</code> if the cargo information is incorrect, <code>false</code> otherwise.
     * @throws SQLException If a database error occurs while fetching the branches.
     */
    public static boolean isIncorrectCargoInfo(String type, String receiverFullname, int departureBranchId,
                                               int destinationBranchId, String weight, String height, String length, String width)
            throws SQLException, DaoException {
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

    /**
     * Verifies if the given calculate info for a cargo is incorrect.
     *
     * @param departureBranchId   the id of the departure branch of the cargo
     * @param destinationBranchId the id of the destination branch of the cargo
     * @param weight              the weight of the cargo
     * @param height              the height of the cargo
     * @param length              the length of the cargo
     * @param width               the width of the cargo
     * @return true if the cargo info is incorrect, false otherwise
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public static boolean isIncorrectCalculateInfo(int departureBranchId, int destinationBranchId, int weight, int height, int length, int width) throws SQLException, DaoException {

        if (weight < 1 || height < 1 || length < 1 || width < 1) {
            return true;
        }
        if (weight > 1000 || height > 1000 || length > 1000 || width > 1000) {
            return true;
        }

        if (departureBranchId == 0 || destinationBranchId == 0) {
            return true;
        }

        BranchService branchService = new BranchService(BranchDao.getInstance());

        return branchService.getBranchById(departureBranchId) == null || branchService.getBranchById(destinationBranchId) == null;
    }
}
