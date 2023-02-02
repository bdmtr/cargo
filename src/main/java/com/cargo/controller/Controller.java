package com.cargo.controller;

import com.cargo.controller.command.Command;
import com.cargo.controller.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The `Controller` class extends the `HttpServlet` class and provides a central point of control
 * for handling HTTP requests. The `doGet` and `doPost` methods are both overridden to delegate
 * the processing of the HTTP requests to the `process` method.
 * <p>
 * The `process` method retrieves the value of the "action" parameter from the request and uses
 * it to look up the appropriate `Command` object from the `CommandContainer`. The `execute`
 * method of the `Command` object is then called to handle the request and produce a response.
 * <p>
 * If any exceptions are thrown during the processing of the request, they are caught and logged
 * with the `LOGGER` instance.
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (SQLException | ServletException | IOException e) {
            LOGGER.error("doGet Fails", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (SQLException | ServletException | IOException e) {
            LOGGER.error("doPost Fails", e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String commandName = request.getParameter("action");
        Command command = CommandContainer.getCommand(commandName);

        String page = command.execute(request, response);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
       }
    }
}