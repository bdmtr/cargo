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

/**
 * The class implements the Command interface and is used to handle the registration process.
 *
 * @see Command
 * @see UserService
 * @see User
 * @see Role
 * @see Validator
 */
public class RegisterCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);
    private final UserService userService;

    /**
     * Constructs a RegisterCommand with the specified UserService.
     *
     * @param userService the service for user-related operations
     */
    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Executes the registration process by creating a new User object from the input data, validating the data, and adding the user to the database.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @return the login page if the registration process is ok
     * @throws SQLException if a database error occurs
     */
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