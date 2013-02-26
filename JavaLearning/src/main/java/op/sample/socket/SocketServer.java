package op.sample.socket;

public interface SocketServer extends Runnable {
	public void setSocketServerConfig(SocketServerConfig socketServerConfig);
	public SocketServerConfig getSocketServerConfig();
}
