package com.cargo.util;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ResourceBundle;


public final class PasswordHasher {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");
    private static final Logger LOGGER = Logger.getLogger(PasswordHasher.class);
    private static final String salt = bundle.getString("salt");

    private PasswordHasher() {
    }

    public static String hash(String password) {
        return (BCrypt.hashpw(password, salt));
    }

    public static boolean verify(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}