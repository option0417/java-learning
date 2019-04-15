package tw.com.wd.jwt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


public class HMacUtil {
    public static byte[] calHMac(byte[] data, byte[] keyBytes) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            Key key = new SecretKeySpec(keyBytes, "HmacSHA256");
            hmac.init(key);

            byte[] hmacBytes = hmac.doFinal(data);
            return hmacBytes;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
