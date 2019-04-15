package tw.com.wd.log.impl;

import tw.com.wd.log.LogStore;
import tw.com.wd.log.LogThreadFactory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileLogStore implements LogStore {
    private static final String LOG_FILE_PATH           = System.getProperty("user.dir") + "/log";
    private static final String LOG_FILE_NAME           = "push.log";
    private static final long PERIOD_TIME               = 1000L;
    private static BlockingQueue<String> LOG_QUEUE      = new LinkedBlockingQueue<String>();
    private ExecutorService writeExecutor               = Executors.newSingleThreadExecutor(new LogThreadFactory());
    private FileLogWorker fileLogWorker                 = new FileLogWorker(LOG_QUEUE);
    private Path logPath;
    private boolean isClose;


    public FileLogStore() {
        super();
        logPath = buildLogFilePath();
        isClose = false;
        doWriteProcess();
    }

    private Path buildLogFilePath() {
        Path logFilePath = Paths.get(LOG_FILE_PATH + File.separator + LOG_FILE_NAME);

        // Check if not exist
        if (!logFilePath.toFile().exists()) {
            createLogFilePath(logFilePath.toFile());
        }
        return logFilePath;


    }

    private void createLogFilePath(File logFile) {
        try {
            if (!logFile.getParentFile().exists()) {
                logFile.getParentFile().mkdir();
            }
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeLog(String log) {
        try {
            while (!LOG_QUEUE.offer(log, 10l, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWriteProcess() {
        Thread writeThread = new Thread(fileLogWorker);

        writeThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.err.printf("uncaughtException at %s\n", thread.getName());
                throwable.printStackTrace();
            }
        });
        writeExecutor.submit(writeThread);
    }

    @Override
    public void close() throws IOException {
        this.isClose = true;
        writeExecutor.shutdown();
    }


    private class FileLogWorker implements Runnable {
        private BlockingQueue<String> logQueue;


        public FileLogWorker(BlockingQueue<String> logQueue) {
            this.logQueue = logQueue;
        }

        @Override
        public void run() {
            AsynchronousFileChannel asyncFileChannel = null;
            try {
                while (!isClose) {
                    int size = logQueue.size();
                    List<String> logList = new ArrayList<String>(size);
                    logQueue.drainTo(logList, size);

                    StringBuilder logStringBuilder = new StringBuilder(logList.size() * 100);
                    for (String log : logList) {
                        logStringBuilder.append(log);
                        logStringBuilder.append(System.lineSeparator());
                    }

                    ByteBuffer logByteBuffer = ByteBuffer.wrap(logStringBuilder.toString().getBytes("UTF-8"));

                    Path mPath = fetchPath();

                    asyncFileChannel = AsynchronousFileChannel.open(mPath, StandardOpenOption.WRITE);
                    Future<Integer> operation = asyncFileChannel.write(logByteBuffer, 0L);
                    operation.get();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    asyncFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private Path fetchPath() {
            return Paths.get(LOG_FILE_PATH + File.separator + LOG_FILE_NAME);
        }
    }
}
