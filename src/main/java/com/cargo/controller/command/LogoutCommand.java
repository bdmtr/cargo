package com.cargo.controller.command;

import com.cargo.controller.Path;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }
}
