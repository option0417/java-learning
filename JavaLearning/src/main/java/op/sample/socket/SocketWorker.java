package op.sample.socket;

import java.net.Socket;

public interface SocketWorker extends Runnable {
	public void setSocket(Socket socket);
	public Socket getSocket();
}
