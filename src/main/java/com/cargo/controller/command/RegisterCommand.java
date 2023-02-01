package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.service.UserService;
import com.cargo.util.Validator;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.cargo.controller.Path.PAGE_REGISTER;

public class RegisterCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);

        if (Validator.isIncorrectRegisterInfo(username, fullname, email, password)) {

            LOGGER.warn("Registration failed");
            return PAGE_REGISTER;
        }

        userService.addUser(user);

        LOGGER.info("Register new user: " + username);
        return Path.PAGE_LOGIN;
    }
}