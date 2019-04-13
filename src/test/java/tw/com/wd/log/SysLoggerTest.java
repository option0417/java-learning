package tw.com.wd.log;

import tw.com.wd.log.impl.SysLogger;
import tw.com.wd.log.obj.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

// Format: [yyyy-mm-dd hh:mm:ss]-[LoggerType][Level]-[content]
public class SysLoggerTest {
    private static final String TEST_LOG = "Hello, World";
    private SysLogger sysLogger;
    private FakeLogStore fakeLogStore;

    @Before
    public void beforeTest() {
        fakeLogStore    = new FakeLogStore();
        sysLogger       = new SysLogger(fakeLogStore);
    }

    @Test
    public void testWriteInfoLog() {
        sysLogger.info(TEST_LOG);

        String result = fakeLogStore.getLog();
        System.out.printf("%s\n", result);

        assertTrue(result.contains("System"));
        assertTrue(result.contains(LogLevel.Info.name()));
        assertTrue(result.contains(TEST_LOG));
    }

    @Test
    public void testWriteErrorLog() {
        sysLogger.error(TEST_LOG);

        String result = fakeLogStore.getLog();
        System.out.printf("%s\n", result);

        assertTrue(result.contains("System"));
        assertTrue(result.contains(LogLevel.Error.name()));
        assertTrue(result.contains(TEST_LOG));
    }

    @Test
    public void testWriteErrorLogWithThrowable() {
        Throwable testThrowable = null;
        try {
            int[] a = {1};
            int b = a[2];
        } catch (Throwable t) {
            testThrowable = t;
        }

        sysLogger.error(testThrowable);

        String result = fakeLogStore.getLog();
        System.out.printf("%s\n", result);

        assertTrue(result.contains("System"));
        assertTrue(result.contains(LogLevel.Error.name()));
    }

    @Test
    public void testWriteDebugLog() {
        sysLogger.debug(TEST_LOG);

        String result = fakeLogStore.getLog();
        System.out.printf("%s\n", result);

        assertTrue(result.contains("System"));
        assertTrue(result.contains(LogLevel.Debug.name()));
        assertTrue(result.contains(TEST_LOG));
    }

    @Test
    public void testWriteTraceLog() {
        sysLogger.trace(TEST_LOG);

        String result = fakeLogStore.getLog();
        System.out.printf("%s\n", result);

        assertTrue(result.contains("System"));
        assertTrue(result.contains(LogLevel.Trace.name()));
        assertTrue(result.contains(TEST_LOG));
    }

    private static final class FakeLogStore implements LogStore {
        private String log;

        @Override
        public void writeLog(String log) {
            this.log = log;
        }

        public String getLog() {
            return this.log;
        }

        @Override
        public void close() throws IOException {
            return;
        }
    }

}
