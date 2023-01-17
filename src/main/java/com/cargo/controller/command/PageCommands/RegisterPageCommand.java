package com.cargo.controller.command.PageCommands;

import com.cargo.controller.command.Command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.controller.Path.PAGE_REGISTER;


public class RegisterPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        return PAGE_REGISTER;
    }
}
