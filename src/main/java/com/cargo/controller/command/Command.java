package com.cargo.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class Command {
    /**
     Executes a command.
     @param request the HttpServletRequest object
     @param response the HttpServletResponse object
     @return the result of the command execution
     @throws IOException if an I/O error occurs
     @throws SQLException if a database error occurs
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException;
}
