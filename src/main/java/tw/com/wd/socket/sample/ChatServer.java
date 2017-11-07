package tw.com.wd.socket.sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ChatServer {
	  // Defines the port that this server will listen on
	   final static int SBAP_PORT = 8888;
	    
	   public static void main(String[] args) throws IOException
	   {
	       // Create a new mediator
	       Mediator mediator = new Mediator();
	 
	       // Create a new server object
	       ServerSocket server = new ServerSocket(SBAP_PORT);
	       System.out.println("");
	       System.out.println("Press CTRL+C to terminate.");
	       System.out.println("Waiting for clients to connect...");
	 
	       /**
	        * Main loop of the application.
	        */
	       while (true) {
	           // wait for client to connect
	           Socket socket = server.accept();
	           System.out.println("Client connected.");
	           show(socket);
	 
	           // ok, connected, 
	           //now create ChatService to handle this connection
	           ChatService service = new ChatService(socket, mediator);
	 
	           // tell the JVM to run it in a thread
	           Thread thread = new Thread(service);
	           thread.start();
	            
	           // now, we go back to wait for next connection.
	       }
	   }
	   
	   private static void show(Socket socket) throws SocketException {
		   //System.out.println("getChannel : " + socket.getChannel().toString());
		   System.out.println("getInetAddress : " + socket.getInetAddress().toString());
		   System.out.println("getLocalSocketAddress : " + socket.getLocalSocketAddress().toString());
		   System.out.println("getLocalPort : " + socket.getLocalPort());
		   System.out.println("getReceiveBufferSize : " + socket.getReceiveBufferSize());
	   }
}
