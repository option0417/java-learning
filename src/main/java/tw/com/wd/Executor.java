package tw.com.wd;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;


public class Executor {
    public static void main(String[] args) throws Exception {
        String key = "mitake_86136982";
        String srcData = "GxxCby05Fcbkd+F0HYWYY7oiAIGCz6+e6nI4Yp0lC8o=";
        // AES encode


        // AES decode
        decodeByAES();
        return;
    }

    public static void encodeByAES() throws Exception {
        // AES encode
        String key = "mitake_86136982";
        byte[] keyBytes = Arrays.copyOf(key.getBytes(Charset.forName("UTF-8")), 16);
        String srcData = "GxxCby05Fcbkd+F0HYWYY7oiAIGCz6+e6nI4Yp0lC8o=";
        byte[] srcDataBytes = srcData.getBytes(Charset.forName("UTF-8"));
        byte[] encodedBytes = null;

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        encodedBytes = cipher.doFinal(srcDataBytes);


        byte[] base64EncodedByte = Base64.getEncoder().encode(encodedBytes);

        String base64EncodedString = new String(base64EncodedByte);

        System.out.println(base64EncodedString);
        System.out.println(base64EncodedString.equals("tDiqktzSoXTkmfejIM9sdjEdREQZBpNWbFcad3Tx+6TTBASYwc8bpf2I85H/8y7j"));
    }

    public static void decodeByAES() throws Exception {
        // AES encode
        String key = "mitake_86136982";
        byte[] keyBytes = Arrays.copyOf(key.getBytes(Charset.forName("UTF-8")), 16);
        String base64EncodedString = "tDiqktzSoXTkmfejIM9sdjEdREQZBpNWbFcad3Tx+6TTBASYwc8bpf2I85H/8y7j";
        byte[] base64EncodedBytes = base64EncodedString.getBytes(Charset.forName("UTF-8"));
        byte[] encodedBytes = null;
        byte[] decodedBytes = null;

        encodedBytes = Base64.getDecoder().decode(base64EncodedString);

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        decodedBytes = cipher.doFinal(encodedBytes);
        String decodeData = new String(decodedBytes);

        System.out.println(decodeData);
        System.out.printf("%s\n", decodeData.equals("GxxCby05Fcbkd+F0HYWYY7oiAIGCz6+e6nI4Yp0lC8o="));
    }
}