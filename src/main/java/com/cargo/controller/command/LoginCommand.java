package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.User;

import com.cargo.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.util.Validator.isIncorrectLoginInfo;

/**
 * The LoginCommand class is used for processing user authentication and logging
 *
 * @see Command
 * @see UserService
 * @see User
 */
public class LoginCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Implements the abstract method {@code execute} from {@link Command}.
     * This method is used to perform user login and validate the user credentials.
     *
     * @param request  the {@link HttpServletRequest} object
     * @param response the {@link HttpServletResponse} object
     * @return String the URL of the next page to be redirected depending on the users role
     * @throws IOException  if there is an I/O error
     * @throws SQLException if a database access error occurs
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isIncorrectLoginInfo(username)) {
            return Path.PAGE_LOGIN;
        }

        HttpSession session = request.getSession();
        User user = userService.findUserByUsernamePassword(username, password);

        if (user == null) {
            return Path.PAGE_LOGIN;
        }

        session.setAttribute("currentUserId", user.getId());
        session.setAttribute("role", user.getRole().toString());
        session.setAttribute("currentUser", user);
        session.setAttribute("session_order", "");
        session.setAttribute("session_branch_id", "");
        session.setAttribute("session_date", "");
        session.setAttribute("balance", user.getBalance());


        String userRole = String.valueOf(user.getRole());

        if (userRole.equals("MANAGER")) {
            LOGGER.info("Manager logged: " + username);
            return "redirect:controller?action=showmanagerpage";
        }

        if (userRole.equals("USER")) {
            LOGGER.info("User logged: " + username);
            return "redirect:controller?action=showcargospage";
        }

        return "redirect:controller?action=showcargospage";
    }
}