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

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
            LOGGER.info("do get");
        } catch (SQLException | ServletException | IOException e) {
            LOGGER.error("doGet Fails");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
            LOGGER.info("do post");
        } catch (SQLException | ServletException | IOException e) {
            LOGGER.error("doPost Fails");
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String commandName = request.getParameter("action");
        Command command = CommandContainer.getCommand(commandName);
        System.out.println(commandName);

        String page = command.execute(request, response);
        System.out.println(page);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
       }
    }
}