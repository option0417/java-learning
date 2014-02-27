package op.sample.socket.impl;

import op.sample.socket.SocketServer;
import op.sample.socket.SocketServerConfig;
import op.sample.socket.SocketServerFactory;

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
