package com.insert.register.Security;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Security {

    public static String encrypt(String strClearText, String secretKey) throws Exception {
        String encrpytedData = "";

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(secretKey.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            encrpytedData = new String(encrypted);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return encrpytedData;
    }

    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        String decryptedData = "";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(encryptedData.getBytes());
            decryptedData = new String(decrypted);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return decryptedData;
    }

    public static String generateSecretKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 32;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
  
        return generatedString;
    }
    
}
