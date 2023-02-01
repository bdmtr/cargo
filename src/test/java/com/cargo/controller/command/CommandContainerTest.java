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
    void testGetCommand_RegisterCommand() {
        Command command = CommandContainer.getCommand("register");
        assertEquals(command.getClass(), RegisterCommand.class);
    }

    @Test
    void testGetCommand_RegisterPageCommand() {
        Command command = CommandContainer.getCommand("registerpage");
        assertEquals(command.getClass(), RegisterPageCommand.class);
    }

    @Test
    void testGetCommand_LoginCommand() {
        Command command = CommandContainer.getCommand("login");
        assertEquals(command.getClass(), LoginCommand.class);
    }

    @Test
    void testGetCommand_LogoutCommand() {
        Command command = CommandContainer.getCommand("logout");
        assertEquals(command.getClass(), LogoutCommand.class);
    }
}