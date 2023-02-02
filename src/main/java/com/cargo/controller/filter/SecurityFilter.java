package com.cargo.controller.filter;

import com.cargo.controller.Path;
import com.cargo.model.enums.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * The SecurityFilter class implements a filter that checks if the user has sufficient permission to access the requested resource.
 * It retrieves the role of the user from the session and validates it against the list of accessible actions.
 */
public class SecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private static final Map<String, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();

    /**
     * Initializes the filter by loading the roles and their accessible actions from the filter configuration.
     *
     * @param config a filter configuration object
     */
    @Override
    public void init(FilterConfig config) {
        LOGGER.info("Security Filter is initialized");
        accessMap.put(String.valueOf(Role.MANAGER), asList(config.getInitParameter("MANAGER")));
        accessMap.put(String.valueOf(Role.USER), asList(config.getInitParameter("USER")));
        commons = asList(config.getInitParameter("common"));
    }

    /**
     * The doFilter method checks if the user has sufficient permission to access the requested resource.
     * If the user doesn't have permission, an error message is set as an attribute and the user is redirected to the error page.
     *
     * @param request  the servlet request
     * @param response the servlet response
     * @param chain    the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessages);
            request.getRequestDispatcher(Path.PAGE_ERROR404).forward(request, response);
        }
    }

    /**
     * A helper method that checks if the user has sufficient permission to access the requested resource.
     *
     * @param request the servlet request
     * @return true if the user has permission, false otherwise
     */
    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = request.getParameter("action");
        if (commandName == null || commandName.equalsIgnoreCase("")) {
            return true;
        }

        HttpSession session = httpRequest.getSession();
        if (session == null) {
            return true;
        }

        String userRole = (String) session.getAttribute("role");
        if (userRole == null) {
            return commons.contains(commandName);
        }
        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroying");
    }

    /**
     * This method is used to parse a string into a list of strings.
     * The string is tokenized by white space and each token is added to the list.
     *
     * @param param the string to be tokenized and added to the list
     * @return the list of tokens
     */
    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}