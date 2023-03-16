package com.cargo.util;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.UserDao;
import com.cargo.model.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ValidatorTest {

    @Test
    void isIncorrectRegisterInfoWhenUsernameIsEmpty() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("", "", "", ""));
    }

    @Test
    void isIncorrectRegisterInfoWhenFullNameIsNull() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("username", null, "email", "password"));
    }

    @Test
    void isIncorrectRegisterInfoWhenUsernameIsNull() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo(null, "fullName", "email", "password"));
    }

    @Test
    void isIncorrectRegisterInfoWhenFullNameIsEmpty() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("username", "", "email", "password"));
    }

    @Test
    void isIncorrectRegisterInfoWhenUsernameLengthMoreThan15T() throws SQLException {
        String username = "1234567890123456";
        String fullName = "fullName";
        String email = "email@gmail.com";
        String password = "password";

        boolean actual = Validator.isIncorrectRegisterInfo(username, fullName, email, password);
        assertTrue(actual);
    }

    @Test
    void isIncorrectLoginInfoTest() {
        boolean actual = Validator.isIncorrectLoginInfo("");
        assertTrue(actual);
    }

    @Test
    void isIncorrectCargoInfo() throws SQLException, DaoException {

        boolean actual = Validator.isIncorrectCargoInfo("user", "", 0, 0, "", "", "", "");
        assertTrue(actual);
    }

    @Test
    void isIncorrectCalculateInfo() throws SQLException, DaoException {
        boolean actual = Validator.isIncorrectCalculateInfo(0, 0, -1,-1, -1, -1);
        assertTrue(actual);
    }
}