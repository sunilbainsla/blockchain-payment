package com.sapient.payment.paymentservice;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * This class used for encrption and decryption
 * @author sku134
 */
public class Base64Encryption {
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static KeySpec keySpec;
    private static SecretKeyFactory secretKeyFactory;
    private static Cipher cipher;
    static byte[] arrayBytes;
    private static String encryptionKey;
    private static String encryptionScheme;
    static SecretKey secretKey;

    private Base64Encryption() {
    }

    public static void main(String[] args)
            throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        System.out.println(encryptStr("Sunil sent 10 pounds to Gitansh"));
    }

    private static void initializeBase64() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        encryptionKey = "c2FwaWVudGJsb2NrY2hhaW5mb3JsbG95ZHM=";
        encryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = encryptionKey.getBytes(UNICODE_FORMAT);
        keySpec = new DESedeKeySpec(arrayBytes);
        secretKeyFactory = SecretKeyFactory.getInstance(encryptionScheme);
        cipher = Cipher.getInstance(encryptionScheme);
        secretKey = secretKeyFactory.generateSecret(keySpec);
    }

    /**
     * Encrypt the string
     * @param unencryptedString
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public static String encryptStr(String unencryptedString)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        initializeBase64();

        String encryptedString = null;
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = new String(Base64.getEncoder().encodeToString(encryptedText));
        return encryptedString;
    }

    /**
     * Decrypt the String
     * @param encryptedString
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public static String decryptStr(String encryptedString)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        initializeBase64();

        String decryptedText = null;
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedText = Base64.getDecoder().decode(encryptedString.getBytes());
        byte[] plainText = cipher.doFinal(encryptedText);
        decryptedText = new String(plainText);

        return decryptedText;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
