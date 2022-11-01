package com.cruisecompany.util.password;

import com.cruisecompany.exception.EncryptionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordEncryption {
    final static Logger logger = LogManager.getLogger(PasswordEncryption.class);
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int ITERATIONS = 512;
    private static final int KEY_LENGTH = 128;
    private static final int SALT_BYTES = 32;


    public static String hashPassword(String password, String salt) throws EncryptionException {
        try {
            char[] passwordChars = password.toCharArray();
            byte[] saltBytes = salt.getBytes();

            PBEKeySpec spec = new PBEKeySpec(
                    passwordChars,
                    saltBytes,
                    ITERATIONS,
                    KEY_LENGTH
            );
            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashedPassword = key.generateSecret(spec).getEncoded();
            return String.format("%x", new BigInteger(hashedPassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Password encryption failed!", e);
            throw new EncryptionException(e);
        }
    }

    public static boolean comparePasswords(String oldPassword, String newPassword, String salt) throws EncryptionException {
        return oldPassword.equals(hashPassword(newPassword, salt));
    }

    public static String generateSalt() {
        byte[] saltBytes = new byte[SALT_BYTES];
        RANDOM.nextBytes(saltBytes);
        return String.format("%x", new BigInteger(saltBytes));
    }
}
