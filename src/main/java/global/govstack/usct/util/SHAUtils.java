package global.govstack.usct.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  private static final String OUTPUT_FORMAT = "%-20s:%s";

  public static String generateSHA(String input, String algorithm) {
    byte[] shaInBytes = digest(input.getBytes(UTF_8), algorithm);
    return bytesToHex(shaInBytes);
  }

  private static byte[] digest(byte[] input, String algorithm) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e);
    }
    byte[] result = md.digest(input);
    return result;
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
