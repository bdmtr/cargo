package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.model.UserDao;
import com.cargo.model.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.controller.Validation.Validator.isIncorrectLoginInfo;

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isIncorrectLoginInfo(request, username)) {
            return Path.PAGE_LOGIN;
        }

        HttpSession session = request.getSession();

        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernamePassword(username, password);

        if (user == null) {
            return Path.PAGE_LOGIN;
        }

        session.setAttribute("currentUserId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("currentUser", user);

        String userRole = String.valueOf(user.getRole());

        if (userRole.equals("MANAGER")) {
            return "redirect:controller?action=showmanagerpage";
        }

        return "redirect:controller?action=showcargospage";
    }
}