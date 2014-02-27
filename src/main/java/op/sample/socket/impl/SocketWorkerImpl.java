package op.sample.socket.impl;

import java.net.Socket;

import op.sample.socket.SocketWorker;

public class SocketWorkerImpl extends Thread implements SocketWorker {
	private Socket socket;
	
	
	public SocketWorkerImpl(Socket socket) {
		super();
		setSocket(socket);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public Socket getSocket() {
		return this.socket;
	}
}
