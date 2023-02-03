package com.cargo.controller.command;

import com.cargo.controller.Path;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The LogoutCommand class implements the logout functionality for the application.
 *
 *  @see Command
 */
public class LogoutCommand extends Command {

    /**
     * This method invalidates the current user's session, which logs them out of the application.
     *
     * @param request  HttpServletRequest object that contains the request the client has made of the servlet
     * @param response HttpServletResponse object that contains the response the servlet sends to the client
     * @return String the path to the login page
     * @throws IOException  if an input or output error is detected when the servlet handles the request
     * @throws SQLException if a database access error occurs or the generated SQL statement does not return a result set
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("currentUserId", null);
            session.setAttribute("role", null);
        }
        return Path.PAGE_LOGIN;
    }
}