package com.cargo.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The AttributeListener class fix the creation and destroying of session
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        LOGGER.info("Session Created:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.info("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }
}