package com.cargo.model.dao;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDaoTest {
    UserDao userDao = mock(UserDao.class);
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);
    private static final User user = new User();

    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setUsername("username");
        user.setFullname("fullname");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setBalance(100);
    }


    @Test
    void findUserByIdTest() throws SQLException {
        when(userDao.findUserById(1)).thenReturn(user);
        assertEquals(user, userDao.findUserById(1));
    }

    @Test
    void getInstanceTest() {
        userDao = UserDao.getInstance();
        boolean exist = userDao != null;
        assertTrue(exist);
    }

    @Test
    void findUserByUsername() throws SQLException {
        when(userDao.findUserByUsername("username")).thenReturn(user);
        assertEquals(user.getUsername(), userDao.findUserByUsername("username").getUsername());
    }

    @Test
    void findUserByUsernamePassword() throws SQLException {
        when(userDao.findUserByUsernamePassword("username", "password")).thenReturn(user);
        assertEquals(user, userDao.findUserByUsernamePassword("username", "password"));
    }


    @Test
    void updateUserProfile() {
        userDao.updateUserProfile(user);
        verify(userDao).updateUserProfile(user);
        assertDoesNotThrow(() -> userDao.updateUserProfile(user));
    }

    @Test
    void changeBalance() throws SQLException {
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);

        userDao.changeBalance(user.getId(), 220);
        verify(userDao).changeBalance(1, 220);
        assertDoesNotThrow(() -> userDao.changeBalance(1, 220));
    }

    @Test
    void addUser() throws SQLException {
        userDao.addUser(user);
        verify(userDao).addUser(user);
        assertDoesNotThrow(() -> userDao.addUser(user));
    }
}
