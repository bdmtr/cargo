package com.cargo.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;


/**
 * The AttributeListener class listens to the changes in the attributes of the ServletContext and logs the changes.
 */
public class AttributeListener implements ServletContextAttributeListener {
    private static final Logger LOGGER = Logger.getLogger(AttributeListener.class);

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        LOGGER.info("ServletContext attribute added::{" + servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        LOGGER.info("ServletContext attribute removed::{" + servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        LOGGER.info("ServletContext attribute replaced::{" + servletContextAttributeEvent.getName() + ", " + servletContextAttributeEvent.getValue() + "}");
    }
}
