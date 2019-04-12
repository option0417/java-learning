package tw.com.wd.jwt;

import java.util.Base64;


public class Base64Util {
    public static final String toBase64(byte[] data) {
        Base64.Encoder encoder = Base64.getUrlEncoder();

        byte[] encodedData = encoder.withoutPadding().encode(data);

        return new String(encodedData);
    }


    public static final byte[] fromBase64(String base64String) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        byte[] decodedData = decoder.decode(base64String);

        return decodedData;
    }
}
