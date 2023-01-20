package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CalculatePageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(CalculatePageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        LOGGER.info("Calculate page loaded successfully");

        return Path.PAGE_CALCULATOR;
    }
}