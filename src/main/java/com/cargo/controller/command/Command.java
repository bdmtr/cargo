package com.cargo.controller.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class Command {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException;
}
