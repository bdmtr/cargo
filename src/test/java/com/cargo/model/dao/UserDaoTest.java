package com.cargo.model.dao;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserDaoTest {
    private UserDao userDao;
    private UserService userService;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        userDao = new UserDao();

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("John");
        when(resultSet.getString("fullname")).thenReturn("John");
        when(resultSet.getString("email")).thenReturn("john@gmail.com");
        when(resultSet.getString("password")).thenReturn("12345");
        when(resultSet.getString("role")).thenReturn("USER");
    }

    @Test
    @DisplayName("Should return null when the user is not found")
    void findUserByIdWhenUserIsNotFoundThenReturnNull() throws SQLException {
        int id = 1;
        User user = userDao.findUserById(id);
        assertNull(user);
    }

    @Test
    @DisplayName("Should return the user when the user is found")
    void findUserByIdWhenUserIsFoundThenReturnTheUser() throws SQLException {
        User user = userDao.findUserById(1);
        assertEquals(1, user.getId());
        assertEquals("John", user.getUsername());
        assertEquals("John", user.getFullname());
        assertEquals("john@gmail.com", user.getEmail());
        assertEquals("12345", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }
}