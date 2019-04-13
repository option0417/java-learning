package tw.com.wd.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class TimeNioSocketServer implements Server {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SimpleDateFormat simpleDateFormat;


    public TimeNioSocketServer() {
        this("127.0.0.1", 8090);
    }

    public TimeNioSocketServer(String host, int port) {
        super();

        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.selector = Selector.open();

            this.serverSocketChannel.configureBlocking(false);

            this.serverSocketChannel.socket().bind(new InetSocketAddress(host, port), 1024);
            this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);

            this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void start() {
        while (true) {
            try {
                this.selector.select(1000l);

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeySet.iterator();
                SelectionKey selectionKey =  null;

                while (iter.hasNext()) {
                    selectionKey = iter.next();
                    iter.remove();

                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();

                        if (selectionKey != null) {
                            selectionKey.cancel();

                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();


                try {
                    this.selector.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(this.selector, SelectionKey.OP_READ);
            }

            if (selectionKey.isReadable()) {
                SocketChannel sc = (SocketChannel) selectionKey.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);

                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);

                    String query = new String(bytes, "UTF-8");
                    System.out.printf("Time query with %s\n", query);

                    sc.configureBlocking(false);
                    sc.register(this.selector, SelectionKey.OP_WRITE);
                }
            }

            if (selectionKey.isWritable()) {
                SocketChannel sc = (SocketChannel) selectionKey.channel();
                String currDateTimeText = this.simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.printf("Curr: %s\n", currDateTimeText);

                byte[] respBytes = currDateTimeText.getBytes();

                try {
                    sc.write(ByteBuffer.wrap(respBytes));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
