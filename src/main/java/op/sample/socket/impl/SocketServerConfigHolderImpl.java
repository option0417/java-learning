package op.sample.socket.impl;

import java.util.HashMap;
import java.util.Map;

import op.sample.socket.SocketServerConfig;
import op.sample.socket.SocketServerConfigHolder;

public class SocketServerConfigHolderImpl implements SocketServerConfigHolder {
	private static SocketServerConfigHolder socketServerConfigHolder;
	private static Map<String, SocketServerConfig> socketServerConfigMap;
	
	
	private SocketServerConfigHolderImpl() {
		super();
		socketServerConfigMap = new HashMap<String, SocketServerConfig>();
	}	

	public static SocketServerConfigHolder getInstance() {
		if (socketServerConfigHolder == null) {
			socketServerConfigHolder = new SocketServerConfigHolderImpl();
		}
		return socketServerConfigHolder;
	}
	
	@Override
	public void initialSocketServerConfig() {
		socketServerConfigMap.put("MEPAAA001", createSocketServerConfig(10001, "MEPAAA001"));
		socketServerConfigMap.put("MEPBBB001", createSocketServerConfig(10002, "MEPBBB001"));
	}

	@Override
	public Map<String, SocketServerConfig> getSocketServerConfigs() {
		return socketServerConfigMap;
	}

	@Override
	public SocketServerConfig getSocketServerConfig(String serviceID) {
		return socketServerConfigMap.get(serviceID);
	}
	
	private SocketServerConfig createSocketServerConfig(int socketPort, String serviceID) {
		return new SocketServerConfigImpl(socketPort, serviceID);
	}
}
