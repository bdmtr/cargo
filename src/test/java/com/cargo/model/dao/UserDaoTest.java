package com.cargo.model.dao;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {
    UserDao userDao = Mockito.mock(UserDao.class);
    private static final User user = new User();

    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setUsername("username");
        user.setFullname("fullname");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setBalance(0);
    }

    @Test
    void findUserByIdTest() throws SQLException {
        Mockito.when(userDao.findUserById(1)).thenReturn(user);
        assertEquals(user, userDao.findUserById(1));
    }
}
