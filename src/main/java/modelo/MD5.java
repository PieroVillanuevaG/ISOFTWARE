package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes());
            BigInteger numero = new BigInteger(1,encBytes);
            String encString = numero.toString(16);
            while(encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
