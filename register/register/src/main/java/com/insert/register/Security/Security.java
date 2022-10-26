package com.insert.register.Security;

import java.util.Random;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Security {

    public static String encrypt(String strClearText, String secretKey) {
        String encryptedData = "";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secretKey);
        encryptedData = encryptor.encrypt(strClearText);
       
        return encryptedData;
    }

    public static String decrypt(String encryptedData, String secretKey) {
        String decryptedData = "";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secretKey);
        decryptedData = encryptor.decrypt(encryptedData);
       
        return decryptedData;
    }

    public static String generateSecretKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
  
        return generatedString;
    }
    
}
