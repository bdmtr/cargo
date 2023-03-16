package com.cargo.util;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ResourceBundle;

/**
 * The PasswordHasher class provides static methods to hash and verify passwords using the BCrypt algorithm.
 * The salt used for hashing is retrieved from the application ResourceBundle.
 */
public final class PasswordHasher {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");
    private static final Logger LOGGER = Logger.getLogger(PasswordHasher.class);
    private static final String SALT = bundle.getString("salt");

    /**
     * This is a private constructor to prevent instantiation of the PasswordHasher class.
     */
    private PasswordHasher() {
    }

    /**
     * Returns the hashed password using the BCrypt algorithm and the salt retrieved from the application ResourceBundle.
     *
     * @param password the password to be hashed
     * @return the hashed password
     */
    public static String hash(String password) {
        return (BCrypt.hashpw(password, SALT));
    }

    /**
     * Verifies the password using the BCrypt algorithm and the hash value provided.
     *
     * @param password the password to be verified
     * @param hash     the hash value to be compared with
     * @return true if the password matches the hash value, false otherwise
     */
    public static boolean verify(String password, String hash) {
        LOGGER.info("Verify password");
        return BCrypt.checkpw(password, hash);
    }
}