package tw.com.wd.example;


import java.io.UnsupportedEncodingException;

public class XXXUtil {
    public int toInt(String text) throws UnsupportedEncodingException {
        return toInt(text.getBytes("UTF8"));
    }

    private int toInt(byte[] textBytes) {
        int result = 0;
        for (byte b : textBytes) {
            if ((int)b <= 90) {
                result += lowerCase(b);
            } else {
                result += upperCase(b);
            }
        }
        return result;
    }

    private int lowerCase(byte b) {
        return (int)b - 65 + 1;
    }

    private int upperCase(byte b) {
        return ((int)b - 97 + 1) * 10;
    }
}
