package tw.com.wd.socket.impl;

import tw.com.wd.socket.SocketServer;
import tw.com.wd.socket.SocketServerConfig;
import tw.com.wd.socket.SocketServerFactory;

public class SocketServerFactoryImpl implements SocketServerFactory {
	private static SocketServerFactory socketServerFactory = null;
	
	
	private SocketServerFactoryImpl() {
		super();
	}
	
	public static SocketServerFactory getInstance() {
		if (socketServerFactory == null) {
			socketServerFactory = new SocketServerFactoryImpl();
		}
		return socketServerFactory;
	}
	
	@Override
	public SocketServer createSocketServer(SocketServerConfig socketServerConfig) {
		// TODO Auto-generated method stub
		return null;
	}

}
