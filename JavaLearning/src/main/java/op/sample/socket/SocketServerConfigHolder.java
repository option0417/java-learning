package op.sample.socket;

import java.util.Map;

public interface SocketServerConfigHolder {
	public void initialSocketServerConfig();
	public Map<String, SocketServerConfig> getSocketServerConfigs();
	public SocketServerConfig getSocketServerConfig(String serviceID);
}
