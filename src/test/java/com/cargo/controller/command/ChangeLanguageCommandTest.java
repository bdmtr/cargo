package com.cargo.controller.command;

import com.cargo.controller.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeLanguageCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);

    private ChangeLanguageCommand changeLanguageCommand;

    @BeforeEach
    void setUp() {
        changeLanguageCommand = new ChangeLanguageCommand();
    }

    @Test
    void executeWhenUserRoleIsUserThenReturnShowCargosPage() throws IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("USER");
        String actual = changeLanguageCommand.execute(request, response);
        assertEquals(Path.PAGE_SHOW_CARGOS, actual);
    }

    @Test
    void executeWhenUserRoleIsNullThenReturnLoginPage() throws IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(null);
        String actual = changeLanguageCommand.execute(request, response);
        assertEquals(Path.PAGE_LOGIN, actual);
    }


    @Test
    void executeWhenUserRoleIsManagerThenReturnManagerPage() throws IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("MANAGER");
        String actual = changeLanguageCommand.execute(request, response);
        assertEquals(Path.PAGE_MANAGER, actual);
    }
}