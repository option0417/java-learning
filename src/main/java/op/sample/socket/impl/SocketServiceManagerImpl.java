package op.sample.socket.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import op.sample.socket.SocketServer;
import op.sample.socket.SocketServerConfig;
import op.sample.socket.SocketServerConfigHolder;
import op.sample.socket.SocketServerFactory;
import op.sample.socket.SocketServiceManager;

public class SocketServiceManagerImpl implements SocketServiceManager {
	private SocketServerConfigHolder socketServerConfigHolder;
	private SocketServerFactory socketServerFactory;
	private ExecutorService service;
	
	
	public SocketServiceManagerImpl() {
		super();
		service = Executors.newFixedThreadPool(5);
		socketServerConfigHolder = SocketServerConfigHolderImpl.getInstance();
		socketServerFactory = SocketServerFactoryImpl.getInstance();
	}
	
	@Override
	public void initialSocketServer() {
		Collection<SocketServerConfig> socketServerConfigCollection =
				socketServerConfigHolder.getSocketServerConfigs().values();
		Iterator<SocketServerConfig> socketServerConfigIter = socketServerConfigCollection.iterator();
		
		while (socketServerConfigIter.hasNext()) {
			SocketServerConfig socketServerConfig = socketServerConfigIter.next();
			SocketServer socketServer = socketServerFactory.createSocketServer(socketServerConfig);
			service.submit(socketServer);
		}
	}

	@Override
	public void destorySocketService() {
		service.shutdown();
	}
}
