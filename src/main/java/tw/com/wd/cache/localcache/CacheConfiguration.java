package tw.com.wd.cache.localcache;

import tw.com.wd.cache.localcache.obj.CacheStrategyType;

import java.io.InputStream;
import java.util.Properties;

public class CacheConfiguration {
    private static final String KEY_CACHE_CAPACITY  = "cache_capacity";
    private static final String KEY_CACHE_STRATEGY  = "cache_strategy";
    private int cacheCapcity;
    private CacheStrategyType cacheStrategy;


    private CacheConfiguration() {
        super();
        this.cacheCapcity   = 10;
        this.cacheStrategy  = CacheStrategyType.LRU;
    }

    public static final CacheConfiguration buildCacheConfiguration() {

        CacheConfiguration cacheConfiguration = new CacheConfiguration();

        if (readFromProperties(cacheConfiguration)) {
            System.out.printf("Read from properties ok\n");
        } else {
            System.err.printf("Read from properties fail\n");
        }

        return cacheConfiguration;
    }

    public static final CacheConfiguration buildCacheConfiguration(int cacheCapcity) {
        return buildCacheConfiguration(cacheCapcity, CacheStrategyType.LRU);
    }

    public static final CacheConfiguration buildCacheConfiguration(int cacheCapcity, CacheStrategyType cacheStrategy) {
        CacheConfiguration cacheConfiguration   = new CacheConfiguration();
        cacheConfiguration.cacheCapcity         = cacheCapcity;
        cacheConfiguration.cacheStrategy        = cacheStrategy;

        return cacheConfiguration;
    }

    private static boolean readFromProperties(CacheConfiguration cacheConfiguration) {
        try {
            InputStream is = CacheConfiguration.class.getClassLoader().getResourceAsStream("cache.properties");
            Properties props = new Properties();
            props.load(is);

            cacheConfiguration.cacheCapcity = Integer.parseInt(props.getProperty(KEY_CACHE_CAPACITY));
            cacheConfiguration.cacheStrategy    = CacheStrategyType.valueOf(props.getProperty(KEY_CACHE_STRATEGY));

            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    public int getCacheCapcity() {
        return cacheCapcity;
    }

    public CacheStrategyType getCacheStrategy() {
        return cacheStrategy;
    }
}
