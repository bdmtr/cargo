package com.cargo.controller.command;

import com.cargo.model.UserDao;
import com.cargo.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class EditProfileCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditProfileCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        int userID = (int) session.getAttribute("currentUserId");
        User user = userDao.findUserById(userID);

        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setFullname(fullname);
        user.setPassword(password);
        userDao.updateUserProfile(user);

        session.setAttribute("currentUserId", userID);
        session.setAttribute("username", userDao.findUserById(userID).getUsername());
        session.setAttribute("currentUser", userDao.findUserById(userID));

        if (userDao.findUserById(userID).getRole().toString().equals("MANAGER")) {
            return "redirect:controller?action=showmanagerpage";
        }


        LOGGER.info("Editing profile: " + username);
        return "redirect:controller?action=showcargospage";
    }
}