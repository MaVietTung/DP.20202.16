package utils;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtils {
    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    public static String md5(String message) {
        String cipherText = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder stringBuilder = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b & 0xff));
            }
            cipherText = stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            cipherText = "";
        }
        return cipherText;
    }
}
