package tw.com.wd.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeSocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("127.0.0.1", 8090);
                        socket.setSoTimeout(30000);

                        OutputStream os = socket.getOutputStream();
                        os.write("UTC+08:00:00".getBytes());

                        byte[] resp = new byte[19];

                        InputStream ins = socket.getInputStream();
                        int readCount = ins.read(resp);

                        if (readCount > 0) {
                            System.out.printf("Resp: %s\n", new String(resp));
                        } else {
                            System.out.printf("No response.\n");
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        // ...
                    }
                }
            }).start();
            Thread.sleep(1l);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Cost: " + (endTime - startTime) + " ms");
    }
}
