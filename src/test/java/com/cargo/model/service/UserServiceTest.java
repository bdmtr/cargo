package com.cargo.model.service;

import com.cargo.exceptions.InvalidCredentialsException;
import com.cargo.model.dao.UserDao;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.util.PasswordHasher;
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
    void findUserByUsernamePasswordTest() throws SQLException, InvalidCredentialsException {
        User testUser = new User("username", PasswordHasher.hash("password"));
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        Mockito.when(userDaoMock.findUserByUsername("username")).thenReturn(testUser);
        Mockito.when(userDaoMock.findUserByUsernamePassword("username", testUser.getPassword())).thenReturn(testUser);
        UserService service = new UserService(userDaoMock);
        User result = service.findUserByUsernamePassword("username", "password");
        assertEquals(testUser, result);
        Mockito.verify(userDaoMock).findUserByUsername("username");
        Mockito.verify(userDaoMock).findUserByUsernamePassword("username", testUser.getPassword());
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