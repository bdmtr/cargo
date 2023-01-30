package com.cargo.util;

import com.cargo.model.dao.UserDao;
import com.cargo.model.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ValidatorTest {

    @Test
    @DisplayName("Should return true when username is empty")
    void isIncorrectRegisterInfoWhenUsernameIsEmpty() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("", "", "", ""));
    }

    @Test
    @DisplayName("Should return true when fullname is null")
    void isIncorrectRegisterInfoWhenFullNameIsNull() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("username", null, "email", "password"));
    }

    @Test
    @DisplayName("Should return true when username is null")
    void isIncorrectRegisterInfoWhenUsernameIsNull() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo(null, "fullName", "email", "password"));
    }

    @Test
    @DisplayName("Should return true when fullname is empty")
    void isIncorrectRegisterInfoWhenFullNameIsEmpty() throws SQLException {
        assertTrue(Validator.isIncorrectRegisterInfo("username", "", "email", "password"));
    }

    @Test
    @DisplayName("Should return true when username length more than 15")
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
    void isIncorrectCargoInfo() throws SQLException {

        boolean actual = Validator.isIncorrectCargoInfo("user", "", 0, 0, "", "", "", "");
        assertTrue(actual);
    }

    @Test
    void isIncorrectCalculateInfo() throws SQLException {
        boolean actual = Validator.isIncorrectCalculateInfo(0, 0, -1,-1, -1, -1);
        assertTrue(actual);
    }

}