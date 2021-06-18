package com.zipc.garden.webplatform.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class manages the mechanism for creating a secure password.
 */
public class PasswordUtil {

    /** Algorithm for password security */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /** Stretching count */
    private static final int ITERATION_COUNT = 10000;

    /** The length of the generated key */
    private static final int KEY_LENGTH = 256;

    /**
     * Generates a secure password from plaintext passwords and salt and returns it
     * @param password Plaintext password
     * @param salt String to add to password
     * @return Secure password
     */
    public static String getSafetyPassword(String password, String salt) {

        char[] passCharAry = password.toCharArray();
        byte[] hashedSalt = getHashedSalt(salt);

        PBEKeySpec keySpec = new PBEKeySpec(passCharAry, hashedSalt, ITERATION_COUNT, KEY_LENGTH);

        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        SecretKey secretKey;
        try {
            secretKey = skf.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        byte[] passByteAry = secretKey.getEncoded();

        StringBuilder sb = new StringBuilder(64);
        for (byte b : passByteAry) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /**
     * The salt is hashed and returned.<br>
     * SHA-256 is used as the hash algorithm.
     * @param salt salt
     * @return Hashed salt
     */
    private static byte[] getHashedSalt(String salt) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }
}
