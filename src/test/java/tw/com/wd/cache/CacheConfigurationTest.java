package tw.com.wd.cache;

import org.junit.Test;
import tw.com.wd.cache.localcache.CacheConfiguration;
import tw.com.wd.cache.localcache.obj.CacheStrategyType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class CacheConfigurationTest {

    @Test
    public void testBuildCacheConfiguration() {
        Throwable rtnThrowable                      = null;
        CacheConfiguration testCacheConfiguration   = null;

        try {
            testCacheConfiguration = CacheConfiguration.buildCacheConfiguration();
        } catch (Throwable t) {
            rtnThrowable = t;
            t.printStackTrace();
        }

        assertThat(rtnThrowable,                                is(nullValue()));
        assertThat(testCacheConfiguration,                      not(nullValue()));
        assertThat(testCacheConfiguration.getCacheCapcity(),       is(1000));
        assertThat(testCacheConfiguration.getCacheStrategy(),   is(CacheStrategyType.LRU));
    }
}
