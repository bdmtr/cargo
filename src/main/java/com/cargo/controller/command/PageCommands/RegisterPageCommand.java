package com.cargo.controller.command.PageCommands;

import com.cargo.controller.command.Command;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.cargo.controller.Path.PAGE_REGISTER;

/**
 * RegisterPageCommand class is responsible for loading the register page.
 */
public class RegisterPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(RegisterPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        LOGGER.info("Register page loaded successfully");
        return PAGE_REGISTER;
    }
}
