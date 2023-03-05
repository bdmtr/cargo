package com.cargo.controller;

import com.cargo.controller.command.Command;
import com.cargo.controller.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The Controller class is a servlet that acts as a central point of control for handling HTTP requests.
 * It extends the HttpServlet class and overrides the doGet and doPost methods to delegate the processing of HTTP requests to the process method.
 * The process method retrieves the value of the "action" parameter from the request and uses it to look up the appropriate Command object from the CommandContainer.
 * The execute method of the Command object is then called to handle the request and produce a response.
 * If any exceptions are thrown during the processing of the request, they are caught and logged with the LOGGER instance.
 * This class is responsible for controlling the flow of the application and invoking the appropriate command to handle the request.
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (Exception e) {
            LOGGER.error("doGet Fails", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (Exception e) {
            LOGGER.error("doPost Fails", e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Command command = CommandContainer.getCommand(request.getParameter("action"));
            String page = command.execute(request, response);

            if (page.contains("redirect:")) {
                String redirectURL = request.getContextPath() + page.substring("redirect:".length());
                response.sendRedirect(redirectURL);
            }
            else if(!page.isEmpty()) {
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (Exception e) {
            LOGGER.error("Failed to process request.", e);
        }
    }
}