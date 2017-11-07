package tw.com.wd.socket.impl;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tw.com.wd.socket.SocketServer;
import tw.com.wd.socket.SocketServerConfig;

public class SocketServerImpl extends Thread implements SocketServer {
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private SocketServerConfig socketServerConfig;
	
	
	public SocketServerImpl(SocketServerConfig socketServerConfig) {
		super();
		setSocketServerConfig(socketServerConfig);
		executorService = Executors.newCachedThreadPool();
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(socketServerConfig.getSocketPort());
			while (true) {
				Socket clientSocket = null;
				clientSocket = this.serverSocket.accept();
				executorService.submit(new SocketWorkerImpl(clientSocket));
			}
		} catch (Exception e) {
			throw new RuntimeException("Error accepting client connection", e);
		}
	}

	@Override
	public void setSocketServerConfig(SocketServerConfig socketServerConfig) {
		this.socketServerConfig = socketServerConfig;
	}

	@Override
	public SocketServerConfig getSocketServerConfig() {
		return socketServerConfig;
	}
}
