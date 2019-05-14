package rtl.tot.corp.mrex.vndm.provider.domain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public final class Util {
  
  /**
   * Genera hash compuesto por las llaves del provedor.
   *
   * @param countryCode codigo del pais del proveedor .
   * @param rut identificador del proveedor .
   * @return String.
   */
  public static String generateKey(String rut, String countryCode) {
    String password = rut + "-" + countryCode;
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    byte[] hashInBytes = Objects.requireNonNull(md).digest(password.getBytes(StandardCharsets.UTF_8));

    StringBuilder sb = new StringBuilder();
    for (byte b : hashInBytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
