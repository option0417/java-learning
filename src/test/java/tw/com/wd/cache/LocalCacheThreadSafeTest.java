package tw.com.wd.cache;

import org.junit.Test;
import tw.com.wd.cache.localcache.CacheConfiguration;
import tw.com.wd.cache.localcache.LocalCache;
import tw.com.wd.cache.localcache.obj.CacheItem;
import tw.com.wd.cache.localcache.obj.CacheItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocalCacheThreadSafeTest {
    private static final String KEY = "KEY";
    private static final int WORKER_SIZE = 2;

    private static class CacheWorker implements Callable<String> {
        private static final int COUNT  = 10;
        private String TEXT_VALUE       = "TEST";
        private int op;
        private LocalCache<String, String> stringLocalCache;

        public CacheWorker(int op, LocalCache<String, String> stringLocalCache) {
            this.op = op;
            this.stringLocalCache = stringLocalCache;
        }

        public String call() throws Exception {
            try {
                CacheItem<String> cacheItem = null;
                switch (this.op) {
                    case 0:
                        cacheItem = CacheItemBuilder.buildStringCacheItem(String.valueOf(System.currentTimeMillis()));
                        stringLocalCache.put(KEY, cacheItem);
                        System.out.printf("PUT %s \n", cacheItem.getValue());
                        break;
                    case 1:
                        cacheItem = stringLocalCache.get(KEY);
                        System.out.printf("GET %s \n", cacheItem.getValue());
                }
                return cacheItem.getValue();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    @Test
    public void testThreadSafe() {
        Throwable t = null;
        ThreadPoolExecutor threadPoolExecutor   = new ThreadPoolExecutor(2, 2, 100l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(2));
        LocalCache<String, String> stringLocalCache     = new LocalCache<String, String>(CacheConfiguration.buildCacheConfiguration(1));
        List<Future<Integer>> futureList        = new ArrayList<Future<Integer>>(WORKER_SIZE);

        CacheItem<String> initCacheItem = CacheItemBuilder.buildStringCacheItem(String.valueOf(System.currentTimeMillis()));
        stringLocalCache.put(KEY, initCacheItem);
        System.out.printf("Init PUT %s \n", initCacheItem.getValue());

        Future<String> getFuture = null;
        Future<String> putFuture = null;
        String getString = null;

        getFuture = threadPoolExecutor.submit(new CacheWorker(1, stringLocalCache));
        putFuture = threadPoolExecutor.submit(new CacheWorker(0, stringLocalCache));

        try {
            putFuture.get();
            getString = getFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            t = e;
        }

        CacheItem<String> cacheItem = stringLocalCache.get(KEY);
        System.out.printf("Final value: %s\n", cacheItem.getValue());


        assertThat(t, is(nullValue()));
        assertThat(cacheItem.getValue(), not(initCacheItem.getValue()));
        assertThat(getString,  is(initCacheItem.getValue()));
    }
}
