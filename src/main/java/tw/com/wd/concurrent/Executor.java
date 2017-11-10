package tw.com.wd.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Executor {
    private ThreadLocal<Integer> INT_LOCAL = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return Integer.valueOf(10);
        }
    };

    public void show() {
        Integer i = INT_LOCAL.get();
        INT_LOCAL.set(i + 1);
        System.out.printf("I'm %s, the int: %d\n", Thread.currentThread().getName(), INT_LOCAL.get());
    }

	public static void main(String[] args) {
	    int numOfThread = 10;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                numOfThread,
                numOfThread,
                100l,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(numOfThread * 2),
                new ThreadFactory() {
                    private AtomicInteger CNT = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("Pool_Thread-" + CNT.getAndIncrement());
                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.err.println("Task " + r.toString() +
                                " rejected from " +
                                executor.toString());

                        if (!executor.isShutdown()) {
                            executor.submit(r);
                        }
                    }
                });
        for (int idx = 1; idx <= numOfThread * 4; idx++) {
            threadPoolExecutor.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            new Executor().show();
                        }
                    }
            );
        }

        threadPoolExecutor.shutdown();

//
//		List<Future<MyInfo>> myInfoThreadList = new ArrayList<Future<MyInfo>>(numOfThread);
//
//
//		long startTime = System.currentTimeMillis();
//		for (int idx = 1; idx <= numOfThread; idx++) {
//			MyInfoThread myInfoThread = new MyInfoThread("MyInfoThread-" + String.valueOf(idx), idx);
//			myInfoThreadList.add(threadPoolExecutor.submit(myInfoThread));
//		}
//
//		for (int idx = 0; idx < numOfThread; idx++) {
//			try {
//				MyInfo myInfo = myInfoThreadList.get(idx).get();
//
//				//System.out.println("Name: " + myInfo.getName());
//				//System.out.println("Count: " + myInfo.getCount());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		long endTime = System.currentTimeMillis();
//        threadPoolExecutor.shutdown();
//
//
//		System.out.printf("TOTAL: %f\n", ((double)(endTime - startTime))/1000d);
//		System.out.printf("CNT: %d\n", MyInfoHandler.getCount());
	}
}
