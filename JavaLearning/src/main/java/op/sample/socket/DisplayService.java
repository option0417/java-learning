package op.sample.socket;

import java.util.Scanner;

public class DisplayService implements Runnable {
    private Scanner scanner;
    
    
    /**
     * Constructor receives a scanner object for i/o operations.
     *
     * @param scanner The source to read input from
     */
    public DisplayService(Scanner scanner) {
        this.scanner = scanner;
    }
     
     
    /**
     * Get input from the scanner and print the line to the output.
     */
    public void run() {
        while (true) {
            if(scanner.hasNext()) {
                String line = scanner.nextLine();
                System.out.println(line);
            } else {
                return;
            }
        }
    }
}
