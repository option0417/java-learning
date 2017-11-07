package tw.com.wd.socket.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tw.com.wd.socket.SocketServer;
import tw.com.wd.socket.SocketServerConfig;
import tw.com.wd.socket.SocketServerConfigHolder;
import tw.com.wd.socket.SocketServerFactory;
import tw.com.wd.socket.SocketServiceManager;

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
