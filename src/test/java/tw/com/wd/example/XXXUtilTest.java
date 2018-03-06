package tw.com.wd.example;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class XXXUtilTest {
    @Test
    public void testWithUpperAndLower() {
        XXXUtil xxxUtil = new XXXUtil();
        int result = 0;

        try {
            result = xxxUtil.toInt("ABcd");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Assert.assertSame(73, result);
    }

    @Test
    public void testWithUpperCase() {
        XXXUtil xxxUtil = new XXXUtil();
        int result = 0;

        try {
            result = xxxUtil.toInt("A");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Assert.assertSame(1, result);
    }

    @Test
    public void testWithLowerCase() {
        XXXUtil xxxUtil = new XXXUtil();
        int result = 0;

        try {
            result = xxxUtil.toInt("a");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Assert.assertSame(10, result);
    }
}
