package tw.com.wd.socket;

public interface SocketServerConfig {
	public void setSocketPort(int socketPort);
	public void setServiceID(String serviceID);	
	public int getSocketPort();
	public String getServiceID();
}
