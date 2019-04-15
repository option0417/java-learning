package tw.com.wd.log;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import tw.com.wd.log.impl.FileLogStore;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class FileLogStoreTest {

    @Test
    public void testWriteToFileLogStore() {
        Exception rtnThrowable = null;
        byte[] resultBytes;
        String resultString;

        try {
            FileLogStore fileLogStore = new FileLogStore();
            fileLogStore.writeLog("Test1");
            fileLogStore.writeLog("Test2");
            fileLogStore.writeLog("Test3");
            Thread.sleep(1000L);
        } catch (Exception t) {
            t.printStackTrace();
            rtnThrowable = t;
        }

        try {
            Path logFilePath = Paths.get(System.getProperty("user.dir") + "/log" + File.separator + "push.log");
            AsynchronousFileChannel asyncFileChannel = AsynchronousFileChannel.open(logFilePath, StandardOpenOption.READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            asyncFileChannel.read(byteBuffer, 0L).get();

            resultBytes = new byte[byteBuffer.position()];
            byteBuffer.flip();
            byteBuffer.get(resultBytes, 0, resultBytes.length);
            resultString = new String(resultBytes);

            assertThat(rtnThrowable, CoreMatchers.is(nullValue()));
            assertThat(resultBytes, not(nullValue()));
            assertThat(resultString, not(nullValue()));
            assertThat(resultString, containsString("Test1"));
            assertThat(resultString, containsString("Test2"));
            assertThat(resultString, containsString("Test3"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
