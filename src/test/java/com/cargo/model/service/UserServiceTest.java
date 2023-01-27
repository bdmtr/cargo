package com.cargo.model.service;

import com.cargo.model.dao.UserDao;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final User testUser = new User();

    @Mock
    UserDao userDaoMock;

    @BeforeEach
    public void setUp() {
        testUser.setId(1);
        testUser.setUsername("username");
        testUser.setFullname("fullname");
        testUser.setEmail("email@gmail.com");
        testUser.setPassword("password");
        testUser.setRole(Role.USER);
        testUser.setBalance(0);
    }

    @Test
    void findUserByIdTest() throws SQLException {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        Mockito.when(userDaoMock.findUserById(1)).thenReturn(testUser);
        assertEquals(testUser, service.findUserById(1));
    }

    @Test
    void findUserByUsernameTest() throws SQLException {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        Mockito.when(userDaoMock.findUserByUsername("username")).thenReturn(testUser);
        assertEquals(testUser, service.findUserByUsername("username"));
    }

    @Test
    void findUserByUsernamePasswordTest() throws SQLException {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        Mockito.when(userDaoMock.findUserByUsernamePassword("username", "password")).thenReturn(testUser);
        assertEquals(testUser, service.findUserByUsernamePassword("username", "password"));
    }

    @Test
    void addUserTest() throws SQLException {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        service.addUser(testUser);
        Mockito.verify(userDaoMock).addUser(testUser);
    }

    @Test
    void updateUserProfileTest() {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        service.updateUserProfile(testUser);
        Mockito.verify(userDaoMock).updateUserProfile(testUser);
    }

    @Test
    void changeBalanceTest() {
        assertNotNull(userDaoMock);
        UserService service = new UserService(userDaoMock);
        service.changeBalance(10, 1);
        Mockito.verify(userDaoMock).changeBalance(10, 1);
    }
}