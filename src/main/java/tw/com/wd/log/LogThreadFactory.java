package tw.com.wd.log;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class LogThreadFactory implements ThreadFactory {
    private static final String NAME_PREFIX     = "LOG-";
    private final AtomicInteger threadNumber    = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(NAME_PREFIX + threadNumber.getAndIncrement());
        return t;
    }
}
