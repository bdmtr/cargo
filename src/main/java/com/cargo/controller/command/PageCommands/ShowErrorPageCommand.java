package com.cargo.controller.command.PageCommands;

import com.cargo.controller.Path;
import com.cargo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return Path.PAGE_ERROR404;
    }

}
