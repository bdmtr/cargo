package com.cargo.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class EditProfileCommandTest {
    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private EditProfileCommand editProfileCommand;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        User user = new User();
        user.setId(1);
        user.setUsername("testUsername");
        user.setFullname("testFullname");
        user.setPassword("testPassword");
    }

    @Test
    void testShouldEditProfileSuccessfully() throws SQLException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("currentUserId")).thenReturn(1);
        when(userService.findUserById(1)).thenReturn(user);
        when(request.getParameter("username")).thenReturn("testUsername");
        when(request.getParameter("fullname")).thenReturn("testFullname");
        when(request.getParameter("password")).thenReturn("testPassword");

        doNothing().when(userService).updateUserProfile(user);

        String result = editProfileCommand.execute(request, response);

        verify(session).setAttribute("currentUserId", 1);
        verify(session).setAttribute("username", "testUsername");
        verify(session).setAttribute("currentUser", user);
        assertEquals("redirect:controller?action=showcargospage", result);
    }
}