package com.cargo.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cargo.controller.Path;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    private LoginCommand loginCommand;

    @BeforeEach
    public void setUp() {
        loginCommand = new LoginCommand(userService);
    }

    @Test
    void testExecuteWhenUserIsNotFound() throws IOException, SQLException{
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);
        when(userService.findUserByUsernamePassword("user", "pass")).thenReturn(null);

        String result = loginCommand.execute(request, response);

        assertEquals(Path.PAGE_LOGIN, result);
    }

    @Test
    void testExecuteWhenUserIsFoundAndRoleIsManager() throws IOException, SQLException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);
        when(userService.findUserByUsernamePassword("user", "pass")).thenReturn(user);
        when(user.getRole()).thenReturn(Role.MANAGER);
        when(user.getId()).thenReturn(0);
        when(user.getBalance()).thenReturn(0);

        String result = loginCommand.execute(request, response);

        assertEquals("redirect:controller?action=showmanagerpage", result);
    }
}