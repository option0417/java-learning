package tw.com.wd.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeNioSocketClient implements Runnable {
    private Selector selector;
    private SocketChannel socketChannel;
    private String host;
    private int port;

    public static void main(String[] args) throws Exception {
        new TimeNioSocketClient().run();
    }


    public TimeNioSocketClient() {
        this("127.0.0.1", 8090);
    }

    public TimeNioSocketClient(String host, int port) {
        super();
        try {
            this.host = host;
            this.port = port;
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (true) {
            try {
                selector.select(1000l);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();

                Iterator<SelectionKey> iter = selectionKeySet.iterator();
                SelectionKey key = null;

                while (iter.hasNext()) {
                    key = iter.next();
                    iter.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        e.printStackTrace();

                        if (key != null) {
                            key.cancel();

                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

//        try {
//            selector.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void doConnect() throws IOException {
        boolean isConnected = socketChannel.connect(new InetSocketAddress(host, port));

        if (isConnected) {
            System.out.printf("isConnected\n");
            socketChannel.register(this.selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            System.out.printf(" no Connected\n");
            socketChannel.register(this.selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        ByteBuffer writeByteBuffer = ByteBuffer.wrap("UTC+08:00:00".getBytes());
        socketChannel.write(writeByteBuffer);

        if (!writeByteBuffer.hasRemaining()) {
            System.out.printf("Send ok!\n");
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();

            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    System.exit(-1);
                }
            } else if (key.isReadable()) {
                ByteBuffer byteBuff = ByteBuffer.allocate(1024);

                int readCount = sc.read(byteBuff);

                if (readCount > 0) {
                    byteBuff.flip();
                    byte[] reqBytes = new byte[byteBuff.remaining()];
                    byteBuff.get(reqBytes);
                    String body = new String(reqBytes);
                    System.out.printf("Read: %s\n", body);
                    System.exit(1);
                } else if (readCount < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
}
