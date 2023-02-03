package com.cargo.controller.command;

import com.cargo.controller.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;

/**
 * ChangeLanguageCommand is used to change the language of the user interface (English or Ukrainian).
 * The selected language will be stored in the current session.
 * English is the default.
 * Based on the user role, the user will be redirected to either the login page, the manager page, or the cargos page.
 */
public class ChangeLanguageCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);
    private static final String LOCALE = "lang";
    private static final String SESSION_LOCALE = "lang";
    private static final String UKRAINE = "ua";
    private static final String ENGLISH = "en";

    private final ArrayList<String> supportedLanguages = new ArrayList<>();

    public ChangeLanguageCommand() {
        supportedLanguages.add(ENGLISH);
        supportedLanguages.add(UKRAINE);
    }

    /**
     * This method is used to change the language of the user interface.
     * It retrieves the selected locale from the HTTP request and sets it in the current session.
     * If the selected locale is not supported, the locale will be set to English by default.
     * Based on the user role, the user will be redirected to either the login page, the manager page, or the cargos page.
     *
     * @param request the {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client.
     * @return the path to the page that the user should be redirected to.
     * @throws IOException if an input or output error is detected when the servlet handles the request.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String locale = request.getParameter(LOCALE);
        HttpSession session = request.getSession(false);
        if (locale != null) {
            if (!supportedLanguages.contains(locale)) {
                locale = ENGLISH;
            }
            session.setAttribute(SESSION_LOCALE, locale);
        }

        String userRole = String.valueOf(session.getAttribute("role"));

        if (userRole != null && userRole.equals("MANAGER")) {
            return Path.PAGE_MANAGER;
        }

        if (userRole != null && userRole.equals("USER")) {
            return Path.PAGE_SHOW_CARGOS;
        }

        LOGGER.info("Language changed");
        return Path.PAGE_LOGIN;
    }
}