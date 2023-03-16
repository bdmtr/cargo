package com.cargo.exceptions;

/**
 A custom exception class for errors that occur in the data access layer.
 */
public class DaoException extends Throwable {
    public DaoException() {}

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
