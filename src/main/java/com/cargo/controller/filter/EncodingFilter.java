package com.cargo.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * EncodingFilter sets the encoding for servlet requests and responses.
 */
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";

    /**
     * This method is called by the web container to indicate to a filter that it is being placed into service.
     * The encoding parameter is read from the filter configuration and if it's not null, it is assigned to the
     * encoding field.
     *
     * @param filterConfig the filter configuration object
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    /**
     * This method is called by the container each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain.
     * The encoding is set for the servlet request and response, and the filterChain.doFilter is called to execute
     * the next filter in the chain.
     *
     * @param servletRequest the request object that is sent to the servlet
     * @param servletResponse the response object that the servlet sends to the client
     * @param filterChain the filter chain that provides access to the next filter in the chain
     * @throws IOException if an I/O error occurs during this filter's processing
     * @throws ServletException if an exception occurs that interferes with this filter's normal operation
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * This method is called by the web container to indicate to a filter that it is being taken out of service.
     */
    @Override
    public void destroy() {

    }
}
