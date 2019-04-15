package tw.com.wd.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TimeSocketServer implements Server {
    private int port;

    public TimeSocketServer(int port) {
        super();
        this.port = port;
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            System.out.printf("TimeSocketServer listen %d started\n", port);

            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors() * 2,
                    3000l, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1000));


            while (true) {
                System.out.printf("Waiting incoming...\n");
                Socket socket = serverSocket.accept();
                System.out.printf("Socket accepted\n");
                poolExecutor.submit(new TimeSocketHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
    }
}
