package com.cargo.controller.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocaleFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private FilterChain chain;

    @InjectMocks
    private LocaleFilter filter;

    @Test
    void testDoFilterDefaultLocale() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("lang")).thenReturn(null);
        when(session.getAttribute("lang")).thenReturn(null);
        filter.doFilter(request, null, chain);
        verify(session).setAttribute("lang", "en");
        verify(chain).doFilter(request, null);
    }
}