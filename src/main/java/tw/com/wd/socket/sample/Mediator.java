package tw.com.wd.socket.sample;

import java.io.PrintWriter;
import java.util.LinkedList;

public class Mediator {
	private LinkedList<PrintWriter> connections;
	 
	 
    /**
     * Constructor creates the list that maintains the connections
     */
    public Mediator() {
        connections = new LinkedList<PrintWriter>();
    }
 
 
    /**
     * Accepts a message and sends it out to all of the clients
     * @param Message the message to broadcast to all connections
     */
    public void writeMessage(String message) {
        System.out.println("Recieved: " + message);
        for (PrintWriter out : connections) {
            out.println(message);
            out.flush();
        }
    }
 
 
    /**
     * Adds a new connection
     * @param Out the new connection to add to the list
     */
    public void add(PrintWriter out) {
        connections.add(out);
    }
 
 
    /**
     * Removes a connection
     * @param Out the connection to remove
     */
    public void remove(PrintWriter out) {
        connections.remove(out);
    }
}
