package com.cargo.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cargo.controller.command.PageCommands.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CommandContainerTest {

    @BeforeAll
    static void setUp() {
    }

    @Test
    void testGetCommandRegisterCommand() {
        Command command = CommandContainer.getCommand("register");
        assertEquals(command.getClass(), RegisterCommand.class);
    }

    @Test
    void testGetCommandRegisterPageCommand() {
        Command command = CommandContainer.getCommand("registerpage");
        assertEquals(command.getClass(), RegisterPageCommand.class);
    }

    @Test
    void testGetCommandLoginCommand() {
        Command command = CommandContainer.getCommand("login");
        assertEquals(command.getClass(), LoginCommand.class);
    }

    @Test
    void testGetCommandLogoutCommand() {
        Command command = CommandContainer.getCommand("logout");
        assertEquals(command.getClass(), LogoutCommand.class);
    }
}