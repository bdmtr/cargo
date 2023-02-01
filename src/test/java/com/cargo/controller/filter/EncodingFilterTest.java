package com.cargo.controller.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EncodingFilterTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    FilterConfig filterConfig = mock(FilterConfig.class);
    FilterChain chain = mock(FilterChain.class);

    @Test
    void testDoFilter() throws ServletException, IOException {
        when(filterConfig.getInitParameter("encoding")).thenReturn("UTF-8");

        EncodingFilter filter = new EncodingFilter();
        filter.init(filterConfig);
        filter.doFilter(request, response, chain);
        verify(request).setCharacterEncoding("UTF-8");
        verify(response).setCharacterEncoding("UTF-8");
    }
}
