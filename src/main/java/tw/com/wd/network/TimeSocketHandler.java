package tw.com.wd.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeSocketHandler implements Runnable {
    private Socket socket;
    private SimpleDateFormat simpleDateFormat;
    private boolean isDone;
    private boolean isOk;


    public TimeSocketHandler(Socket socket) {
        super();
        this.socket = socket;
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public void run() {
        try {
            String query = fetchQuery();
            System.out.printf("Time query with %s\n", query);

            sendCurrentDateTime();

            this.isDone = this.isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.isDone = true;
            this.isOk = false;
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isOk() {
        return isOk;
    }

    private void sendCurrentDateTime() throws IOException {
        String currDateTimeText = this.simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.printf("Curr: %s\n", currDateTimeText);

        OutputStream outs = this.socket.getOutputStream();
        outs.write(currDateTimeText.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * Fetch query bytes from remote
     * @return
     * @throws IOException
     */
    private String fetchQuery() throws IOException {
        byte[] reqBytes = new byte[12];
        InputStream ins = this.socket.getInputStream();
        int readCount = ins.read(reqBytes);

        if (readCount == 12) {
            if (isValid(reqBytes)) {
                return new String(reqBytes);
            }
        }
        throw new IllegalArgumentException();
    }

    private boolean isValid(byte[] reqBytes) {
        return ((reqBytes[0] ^ 0x55) == 0 || (reqBytes[0] ^ 0x75) == 0) &&  // Check 'U' or 'u'
            ((reqBytes[1] ^ 0x54) == 0 || (reqBytes[1] ^ 0x74) == 0) &&     // Check 'T' or 't'
            ((reqBytes[2] ^ 0x43) == 0 || (reqBytes[2] ^ 0x63) == 0) &&     // Check 'C' or 'c'
            ((reqBytes[3] ^ 0x2B) == 0 || (reqBytes[3] ^ 0x2D) == 0) &&     // Check '+' or '-'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39) &&                 // Check '0~9'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39) &&                 // Check '0~9'
            ((reqBytes[6] ^ 0x3A) == 0 ) &&                                 // Check ':'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39) &&                 // Check '0~9'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39) &&                 // Check '0~9'
            ((reqBytes[6] ^ 0x3A) == 0 ) &&                                 // Check ':'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39) &&                 // Check '0~9'
            (reqBytes[4] >= 0x30 && reqBytes[4] <= 0x39)                    // Check '0~9'
            ? true : false;
    }
}
