package com.cargo.controller.command;


import com.cargo.controller.Path;
import com.cargo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;

public class ChangeLanguageCommand extends Command {
    private static final String LOCALE = "lang";
    private static final String SESSION_LOCALE = "lang";
    private static final String UKRAINE = "ua";
    private static final String ENGLISH = "en";

    private final ArrayList<String> supportedLanguages = new ArrayList<>();

    public ChangeLanguageCommand() {
        supportedLanguages.add(ENGLISH);
        supportedLanguages.add(UKRAINE);
    }

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

        if (userRole == null) {
            assert false;
            if (userRole.isEmpty()) {
                return Path.PAGE_LOGIN;
            }
        }

        if (userRole.equals("MANAGER")) {
            return Path.PAGE_MANAGER;
        }

        if (userRole.equals("USER")) {
            return Path.PAGE_SHOW_CARGOS;
        }

        return Path.PAGE_LOGIN;
    }
}
