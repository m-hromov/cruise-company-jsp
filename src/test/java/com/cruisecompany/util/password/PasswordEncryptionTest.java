package com.cruisecompany.util.password;

import com.cruisecompany.exception.EncryptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptionTest {
    final String password = "test";
    final String salt = "test";

    @Test
    void testHashPassword() throws EncryptionException {
        String encrypted1 = PasswordEncryption.hashPassword(password, salt);
        assertNotEquals(password, encrypted1);
        String encrypted2 = PasswordEncryption.hashPassword(password, salt + "test2");
        assertNotEquals(encrypted1, encrypted2);
    }

    @Test
    void comparePasswords() throws EncryptionException {
        String encrypted = PasswordEncryption.hashPassword(password, salt);
        assertTrue(PasswordEncryption.comparePasswords(encrypted, password, salt));
        assertFalse(PasswordEncryption.comparePasswords("test2", password, salt));
    }

    @Test
    void generateSalt() {
        assertNotNull(PasswordEncryption.generateSalt());
        assertNotEquals(PasswordEncryption.generateSalt(), PasswordEncryption.generateSalt());
    }
}