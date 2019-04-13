package tw.com.wd.network;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;


public class TimeSocketHandlerTest {
    @Test
    public void testFetchQuery() {
        FakeSocket fakeSocket = new FakeSocket();

        TimeSocketHandler timeSocketHandler = new TimeSocketHandler(fakeSocket);
        timeSocketHandler.run();
        Assert.assertTrue(timeSocketHandler.isOk());

        System.out.println(new String(fakeSocket.byteArrayOutputStream.toByteArray()));
    }

    @Test
    public void testFetchQueryWithLowerCase() {
        TimeSocketHandler timeSocketHandler = new TimeSocketHandler(new FakeSocket("uTc+08:00:00"));
        timeSocketHandler.run();
        Assert.assertTrue(timeSocketHandler.isOk());
    }

    @Test
    public void testFetchQueryWithWrongLength() {
        TimeSocketHandler timeSocketHandler = new TimeSocketHandler(new FakeSocket("UT+08:00:00"));
        timeSocketHandler.run();
        Assert.assertFalse(timeSocketHandler.isOk());
    }

    @Test
    public void testFetchQueryWithWrongCharacter() {
        TimeSocketHandler timeSocketHandler = new TimeSocketHandler(new FakeSocket("UBC+08:00:00"));
        timeSocketHandler.run();
        Assert.assertFalse(timeSocketHandler.isOk());
    }

    private static final class FakeSocket extends Socket {
        private String queryString;
        private ByteArrayOutputStream byteArrayOutputStream;

        public FakeSocket() {
            this("UTC+08:00:00");
        }

        public FakeSocket(String queryString) {
            super();
            this.queryString = queryString;
            this.byteArrayOutputStream = new ByteArrayOutputStream();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(this.queryString.getBytes(Charset.forName("UTF-8")));
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return this.byteArrayOutputStream;
        }
    }
}
