package tw.com.wd;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Executor {


    public static void main(String[] args) throws Exception {
        String bom = readFromFile();
        String bomText1 = "\uEFBB" + "\uBF00" + "{CS210";
        String bomText2 = "\uFEFF{CS210";
        String bomText3 = "\uFFFE{CS210";
        String text = "{CS210";

        char[] UTF8_BOM      = {0xEF, 0xBB, 0xBF};
        char[] UTF16_BE_BOM  = {0xFE, 0xFF};
        char[] UTF16_LE_BOM  = {0xFF, 0xFE};

        System.out.printf("Text: %s\n", removeBOM(bom));
        //System.out.printf("Text: %s\n", removeBOM(bomText2));
        //System.out.printf("Text: %s\n", removeBOM(bomText3));
        //System.out.printf("Text: %s\n", removeBOM(text));
    }

    private static String readFromFile() {
        try {
            FileInputStream fis = new FileInputStream(new File("/Users/weide_su/Dev/testing/test.log"));
            StringBuilder responseBuilder   = new StringBuilder(30000);
            int charInt                     = -1;

            InputStreamReader is = new InputStreamReader(fis, "UTF-8");

            while ( (charInt = is.read()) != -1) {
                char ch = (char) charInt;
                responseBuilder.append(ch);
            }
            fis.close();
            return responseBuilder.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }


    private static String removeBOM(String responseJSON) throws UnsupportedEncodingException {
        System.out.printf("Text: %s\n", responseJSON);
        byte[] UTF8_BOM     = new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF};
        char UTF16_BE_BOM   = '\uFEFF';
        char UTF16_LE_BOM   = '\uFFFE';

        if(responseJSON.charAt(0) == UTF16_BE_BOM) {
            responseJSON = responseJSON.substring(1);
        } else if(responseJSON.charAt(0) == UTF16_LE_BOM) {
            responseJSON = responseJSON.substring(1);
        } else {
            byte[] jsonBytes = responseJSON.getBytes(Charset.forName("UTF-8"));

            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[0] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[1] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[2] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[3] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[4] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[5] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[6] & 0xFF));
            System.out.printf("byte: %s\n", Integer.toHexString(jsonBytes[7] & 0xFF));
            responseJSON = new String(jsonBytes);

            if (    jsonBytes[0] == UTF8_BOM[0] &&
                    jsonBytes[1] == UTF8_BOM[1] &&
                    jsonBytes[2] == UTF8_BOM[2]) {
                System.out.printf("UTF8_BOM\n");
                byte[] newJSONBytes = new byte[jsonBytes.length-3];
                System.arraycopy(jsonBytes, 2, newJSONBytes, 0, jsonBytes.length-3);
            }
        }
        return responseJSON;
    }
}

