package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import com.cargo.model.service.UserService;
import com.cargo.util.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.cargo.controller.Path.PAGE_REGISTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterCommand registerCommand;
    User user = new User();
    String username = "testuser";
    String fullname = "Test User";
    String email = "testuser@email.com";
    String password = "testpassword";

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);
    }

    @Test
    void correctRegisterUserInfoTest() throws SQLException {
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("fullname")).thenReturn(fullname);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        userService.addUser(user);

        String result = registerCommand.execute(request, response);

        if (Validator.isIncorrectRegisterInfo(username, fullname, email, password)) {
            assertEquals(PAGE_REGISTER, result);
        } else {
            assertEquals(Path.PAGE_LOGIN, result);
        }
    }
}
