package com.cargo.controller.command;

import com.cargo.controller.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogoutCommandTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    @Test
    void testExecute() throws IOException, SQLException {
        when(request.getSession(false)).thenReturn(session);

        LogoutCommand logoutCommand = new LogoutCommand();
        String result = logoutCommand.execute(request, response);
        verify(session).setAttribute("currentUserId", null);
        verify(session).setAttribute("role", null);
        assertEquals(Path.PAGE_LOGIN, result);
    }
}

