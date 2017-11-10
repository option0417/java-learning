package tw.com.wd.concurrent.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CommonThreadPool extends ThreadPoolExecutor {
    public CommonThreadPool(int numOfThread) {
        super(
                numOfThread,
                numOfThread,
                100l,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(numOfThread * 2),
                new CommonThreadFactory(),
                new CommonRejectedExecutionHandler());
    }

    private static class CommonThreadFactory implements ThreadFactory {
        private AtomicInteger CNT = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("PoolThread-" + CNT.getAndIncrement());
            return t;
        }
    }

    private static class CommonRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("Task " + r.toString() + " rejected from " + executor.toString());

            System.out.println("Check ThreadPoolExecutor...");
            if (!executor.isShutdown()) {
                executor.execute(r);
                System.out.println("Task " + r.toString() + "re-send");
            }
        }
    }
}
