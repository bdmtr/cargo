package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MakeCargoPageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(MakeCargoPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        LOGGER.info("Make cargo page loaded successfully");
        return Path.PAGE_MAKE_CARGO;
    }
}

