package spring.demo.helper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by vhphat on 10/8/2016.
 */
public class PasswordEncoder {
    private static final int KEY_LENGTH = 256; // bits
    private static final String SALT = "2f499788259cc923";


    public static String createHash(String password) {
        try {
            char[] passwordChars = password.toCharArray();
            String  salt = SALT;
            byte[] saltBytes = hexStringToByteArray(salt);
            int iterations = 100000;

            PBEKeySpec spec = new PBEKeySpec(
                    passwordChars,
                    saltBytes,
                    iterations,
                    KEY_LENGTH
            );

            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hashedPassword = key.generateSecret(spec).getEncoded();
            return toHex(hashedPassword).toUpperCase();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
