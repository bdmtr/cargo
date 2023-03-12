package com.cargo.controller.command;

import com.cargo.model.entity.User;
import com.cargo.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class `EditProfileCommand` implements the `execute` method to edit the user's profile information.
 * The method retrieves the user data from the `UserService` and updates the profile information.
 * After updating the profile, it redirects the user to the cargo page.
 *
 * @see Command
 * @see UserService
 * @see User
 */
public class EditProfileCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditProfileCommand.class);
    private final UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method retrieves the user information from the session, retrieves the updated user information from the request,
     * updates the user information using the `updateUserProfile` method of the UserService, and sets the updated
     * user information to the session.
     *
     * @param request  The `HttpServletRequest` instance to retrieve the user input from the request.
     * @param response The `HttpServletResponse` instance to redirect the user to the cargo page.
     * @return The URL to redirect the user to the cargo page.
     * @throws IOException  If an input or output exception occurred.
     * @throws SQLException If a database access exception occurred.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("currentUserId");
        User user = userService.findUserById(userID);

        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setFullname(fullname);
        if (!password.isEmpty()) {
            user.setPassword(password);
        }
        userService.updateUserProfile(user);

        session.setAttribute("currentUserId", userID);
        session.setAttribute("username", userService.findUserById(userID).getUsername());
        session.setAttribute("currentUser", userService.findUserById(userID));

        LOGGER.info("Editing profile: " + username);
        return "redirect:controller?action=showcargospage";
    }
}