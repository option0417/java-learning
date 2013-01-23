package op.sample.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatService implements Runnable {
    private Socket socket;
    private Scanner in;
    private Mediator mediator;
    private PrintWriter writer;
 
 
    /**
     * Constructs a service object that processes commands
     * from a socket utilizing the mediator service.
     * @param socket the Socket
     * @param mediator the Mediator
     */
    public ChatService(Socket socket, Mediator mediator) {
        this.socket = socket;
        this.mediator = mediator;
    }
 
 
    /**
     * Add new i/o services and call doService()
     * which performs the primary chat functions.
     *
     * When doService() returns, cleans up and shuts down the thread.
     */
    public void run() {
        try {
            try {
                in = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream());
                mediator.add(writer);
                doService();
            } finally {
                mediator.remove(writer);
                socket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException exception) {
            System.out.println("Serverside error occured.");
            exception.printStackTrace();
        }
    }
 
 
    /**
     * Prints every command received.  If the user enters "QUIT",
     * then the thread terminates.
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
 
            String command = in.nextLine();
            if (command.equals("QUIT")) {
                return;
            } else {
                executeCommand(command);
            }
        }
    }
 
 
    /**
     * Executes a single command.
     * @param command the command to execute
     */
    public void executeCommand(String command) {
        mediator.writeMessage(command);
    }
}
