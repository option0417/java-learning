package tw.com.wd.example;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testAddCorrectWithInt() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 1);

        Assert.assertEquals(2, result);
    }

    @Test
    public void testAddWrongWithInt() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 1);

        Assert.assertNotEquals(1, result);
    }
}
