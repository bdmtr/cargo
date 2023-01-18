package com.cargo.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

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
}
