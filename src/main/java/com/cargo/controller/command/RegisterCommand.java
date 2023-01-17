package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.controller.Validation.Validator;
import com.cargo.model.UserDao;
import com.cargo.model.entity.User;
import com.cargo.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.cargo.controller.Path.PAGE_REGISTER;

public class RegisterCommand extends Command {
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

        if (Validator.isIncorrectRegisterInfo(request, username, fullname, email, password)) {
            return PAGE_REGISTER;
        }

        UserDao userDao = new UserDao();
        userDao.addUser(user);

        return Path.PAGE_LOGIN;
    }
}