package com.cargo.exceptions;

public class InvalidCredentialsException extends CommandException {
    public InvalidCredentialsException(String message) {
        super("Incorrect credentials");
    }
}