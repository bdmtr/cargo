package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.entity.User;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditProfileCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditProfileCommand.class);
    private final UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

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
        user.setPassword(password);
        userService.updateUserProfile(user);

        session.setAttribute("currentUserId", userID);
        session.setAttribute("username", userService.findUserById(userID).getUsername());
        session.setAttribute("currentUser", userService.findUserById(userID));

        if (userService.findUserById(userID).getRole().toString().equals("MANAGER")) {
            return "redirect:controller?action=showmanagerpage";
        }


        LOGGER.info("Editing profile: " + username);
        return "redirect:controller?action=showcargospage";
    }
}