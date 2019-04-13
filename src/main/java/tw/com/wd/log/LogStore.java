package tw.com.wd.log;


import java.io.Closeable;

public interface LogStore extends Closeable {
    /**
     * Backup log to deep store.
     * @param log
     * @return
     */
    public void writeLog(String log);
}
