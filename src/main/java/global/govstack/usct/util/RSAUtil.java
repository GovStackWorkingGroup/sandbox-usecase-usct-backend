package global.govstack.usct.util;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtil {

  public static Key getPrivateKey(String key) {
    try {
      PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(key));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePrivate(keySpecPrivate);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Key getPublicKey(String key) {
    try {
      X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(key));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePrivate(keySpecPublic);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String encrypt(String message, Key key) throws Exception {
    byte[] messageToBytes = message.getBytes();
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedBytes = cipher.doFinal(messageToBytes);
    return encode(encryptedBytes);
  }

  public String decrypt(String encryptedMessage, Key privateKey) throws Exception {
    byte[] encryptedBytes = decode(encryptedMessage);
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
    return new String(decryptedMessage, "UTF8");
  }

  private static String encode(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  private static byte[] decode(String data) {
    return Base64.getDecoder().decode(data);
  }
}
