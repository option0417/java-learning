package op.sample.socket.impl;

import op.sample.socket.SocketServerConfig;

public class SocketServerConfigImpl implements SocketServerConfig {
	private int socketPort;
	private String serviceID;
	
	public SocketServerConfigImpl(int socketPort, String serviceID) {
		setSocketPort(socketPort);
		setServiceID(serviceID);
	}
	
	@Override
	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	@Override
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public int getSocketPort() {
		return socketPort;
	}

	@Override
	public String getServiceID() {
		return serviceID;
	}
}
