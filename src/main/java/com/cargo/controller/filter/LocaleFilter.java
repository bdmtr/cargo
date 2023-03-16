package com.cargo.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LocaleFilter is a Servlet Filter implementation that sets the locale for the application.
 * It retrieves the locale from the request parameter "lang" and sets it in the session.
 * If the "lang" parameter is not provided, it sets the default locale "en".
 */
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale = request.getParameter("lang");
        String defaultLocale = "en";

        if (locale != null) {
            session.setAttribute("lang", locale);
        } else if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", defaultLocale);
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) {
    }

    /**
     * This method is called by the web container to indicate to a filter that it is being taken out of service.
     */
    public void destroy() {
    }

}
