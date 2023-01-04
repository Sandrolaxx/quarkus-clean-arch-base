package com.aktie.domain.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author SRamos
 */
public class EncryptUtil {

    public static String textEncrypt(final String plainText, final String key) {
        
        try {
            var aesKey = new SecretKeySpec(key.getBytes(), "AES");
            var cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            var encrypted = cipher.doFinal(plainText.getBytes());

            return java.util.Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String textDecrypt(final String cipherText, final String key) {
        
        try {
            var aesKey = new SecretKeySpec(key.getBytes(), "AES");
            var cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            var decordedValue = java.util.Base64.getDecoder().decode(cipherText);
            var decValue = cipher.doFinal(decordedValue);

            return new String(decValue);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
