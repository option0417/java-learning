package op.sample.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	  // The port to connect to
    private static final int BCHAT_PORT = 8888;
    private static final String BCHAT_IP = "localhost";
     
    public static void main(String[] args) throws IOException {
 
        // Create the socket and connect to the predefined server:port
        Socket socket = new Socket(BCHAT_IP, BCHAT_PORT);
 
        // Create the input and output streams, from this socket connection
        InputStream instream = socket.getInputStream();
        OutputStream outstream = socket.getOutputStream();
 
        // Create a scanner and print writer for local i/o
        Scanner socketIn = new Scanner(instream);
        PrintWriter socketOut = new PrintWriter(outstream);
        Scanner keyIn = new Scanner(System.in);
 
        // Define the display service and instruct it to listen 
        // to the socket input stream, display all messages the come in.
        DisplayService service = new DisplayService(socketIn);
 
        // Well, do it (display) in a thread so that 
        // we can type with out being blocked by in comming messages.
        Thread thread = new Thread(service);
        thread.start();
 
        // first, get the client name and remember it.
        System.out.print("Enter your name: ");
        String name = keyIn.nextLine();
 
        while (true) {
            // read a line of message from (system/console) input stream 
            String line = keyIn.nextLine();
             
            // write the message with client name to the socket output stream
            socketOut.println(name + ": " + line);
            socketOut.flush();
             
            // if the last message is "QUIT" then stop.
            if ("QUIT".equals(line)) {
                break;
            }
        }
        socket.close();
    }
}
