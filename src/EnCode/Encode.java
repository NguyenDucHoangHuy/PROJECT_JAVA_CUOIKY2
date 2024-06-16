package EnCode;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

public class Encode {
    public static String toSHA1(String password) {
        String key = "skjdfkbahfwwbnaldbsjbd";
        String result = null;

        password = password + key;
        try {
            byte[] dataBytes = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = md.digest(dataBytes);
            result = new String(Base64.encodeBase64(sha1hash), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}