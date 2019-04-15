package tw.com.wd.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;


public class NettyServer {
    private String ipAddress;
    private int port;
    private boolean ssl;


    public NettyServer(String ipAddress, int port) {
        this(ipAddress, port, false);
    }

    public NettyServer(String ipAddress, int port, boolean ssl) {
        super();
        this.ipAddress = ipAddress;
        this.port = port;
        this.ssl = ssl;
    }

    public void initial() throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (this.ssl) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);

            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new NettyServerInitializer(sslCtx));

            Channel ch = b.bind(this.ipAddress, this.port).sync().channel();

            System.out.println("Server started on " + (this.ssl ? "https" : "http") + "://127.0.0.1:" + this.port + '/');

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
